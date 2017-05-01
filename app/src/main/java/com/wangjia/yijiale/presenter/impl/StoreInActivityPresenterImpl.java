package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.iview.StoreInActivityView;
import com.wangjia.yijiale.presenter.StoreInActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class StoreInActivityPresenterImpl extends BasePresenterImpl implements StoreInActivityPresenter {

    private StoreInActivityView view;
    private CacheUtil mCacheUtil;

    public StoreInActivityPresenterImpl(StoreInActivityView view, Context context) {
        if (view == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.view = view;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(String token, String company_name, String company_address_detail, String contacts_name, String contacts_phone) {
//        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().
                store_id(token, company_name, company_address_detail, contacts_name, contacts_phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("商家入驻-收集商家信息：", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean getInfo) {
                        view.hidProgressDialog();
                        view.store_id(getInfo);
                        L.i("商家入驻-收集商家信息：", new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }
}

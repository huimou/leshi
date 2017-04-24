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
    public void getData(String token, String company_name, String company_address, String company_address_detail, String contacts_name,
                        String contacts_phone, String contacts_email, String business_sphere,
                        String business_licence_number, String business_licence_number_elc) {
//        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().
                store_id(token, company_name, company_address, company_address_detail, contacts_name, contacts_phone,
                        contacts_email, business_sphere, business_licence_number, business_licence_number_elc)
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
                        Log.i("个人商家入驻第二步接收店铺信息&身份证信息：", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean getInfo) {
                        view.hidProgressDialog();
                        view.store_id(getInfo);
                        L.i("个人商家入驻第二步接收店铺信息&身份证信息：", new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }
}

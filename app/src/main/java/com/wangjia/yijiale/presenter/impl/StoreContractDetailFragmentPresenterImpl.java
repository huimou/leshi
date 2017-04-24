package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.MyStoreDetail;
import com.wangjia.yijiale.iview.StoreContractDetailFragmentView;
import com.wangjia.yijiale.presenter.StoreContractDetailFragmentPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.SPUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class StoreContractDetailFragmentPresenterImpl extends BasePresenterImpl implements StoreContractDetailFragmentPresenter {

    private StoreContractDetailFragmentView view;
    private CacheUtil mCacheUtil;
    private Context context;

    public StoreContractDetailFragmentPresenterImpl(StoreContractDetailFragmentView view, Context context) {
        if (view==null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.view = view;
        mCacheUtil = CacheUtil.get(context);
        this.context = context;
    }

    @Override
    public void getData(String store_id) {
        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi()
                .my_store_contactinfo(store_id, SPUtils.get(context, Constants.TOKEN,"").toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyStoreDetail>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(MyStoreDetail getInfo) {
                        view.hidProgressDialog();
                        view.getData(getInfo);
                        L.i(new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    /*@Override
    public void getGuokrHotFromCache(int offset) {
        if (mCacheUtil.getAsJSONObject(Config.FirstFragment + offset) != null) {
            GetVersion getVersion = new Gson().fromJson(mCacheUtil.getAsJSONObject(Config.FirstFragment + offset).toString(), GetVersion.class);
            firstFragmentView.getVersion((GetVersion) getVersion);
        }
    }*/
}

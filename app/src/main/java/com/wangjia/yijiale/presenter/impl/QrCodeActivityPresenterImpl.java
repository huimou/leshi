package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.QrCode;
import com.wangjia.yijiale.iview.QrCodeActivityView;
import com.wangjia.yijiale.presenter.QrCodeActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class QrCodeActivityPresenterImpl extends BasePresenterImpl implements QrCodeActivityPresenter {

    private QrCodeActivityView registerActivityView;
    private CacheUtil mCacheUtil;

    public QrCodeActivityPresenterImpl(QrCodeActivityView registerActivityView, Context context) {
        if (registerActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.registerActivityView = registerActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(int store_id) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .shop_code(store_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QrCode>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(QrCode getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.shop_code(getInfo);
                        L.i(new Gson().toJson(getInfo));
                    }
                });
        addSubscription(s);
    }


    @Override
    public void payMoneyForStore(String token,String amount,int store_id) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .payMoneyForStore(token,amount,store_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.payMoneyForStore(baseBean);
                        L.i(new Gson().toJson(baseBean));
                    }
                });
        addSubscription(s);
    }
}

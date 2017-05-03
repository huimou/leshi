package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.entity.ZhifuShiWuBean;
import com.wangjia.yijiale.iview.ChargeActivityView;
import com.wangjia.yijiale.presenter.ChargeActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class ChargeActivityPresenterImpl extends BasePresenterImpl implements ChargeActivityPresenter {

    private ChargeActivityView registerActivityView;
    private CacheUtil mCacheUtil;

    public ChargeActivityPresenterImpl(ChargeActivityView registerActivityView, Context context) {
        if (registerActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.registerActivityView = registerActivityView;
        mCacheUtil = CacheUtil.get(context);
    }


    //会员充值界面显示
    @Override
    public void showVip() {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .showVip(YiApplication.getInstance().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShowVipBean>() {

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
                    public void onNext(ShowVipBean getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.showVip(getInfo);
                        L.i(new Gson().toJson(getInfo));
                    }
                });
        addSubscription(s);
    }

//会员充值下单
    @Override
    public void vipSubmitOrder(String token,String pdr_amount,String payment_method) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .vipSubmitOrder(YiApplication.getInstance().getToken(), pdr_amount,payment_method)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitOrderBean>() {

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
                    public void onNext(SubmitOrderBean bean) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.vipSubmitOrder(bean);
                        L.i(new Gson().toJson(bean));
                    }
                });
        addSubscription(s);
    }


    //实物订单支付页面
    @Override
    public void shiwuOrder(String ply_sn) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .shiwuOrder(YiApplication.getInstance().getToken(),ply_sn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhifuShiWuBean>() {

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
                    public void onNext(ZhifuShiWuBean getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.shiwuOrder(getInfo);
                        L.i(new Gson().toJson(getInfo));
                    }
                });
        addSubscription(s);
    }

    //订单确认支付
    @Override
    public void orderSubmitPlay(String ply_sn,String payment_method) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .orderSubmitPlay(YiApplication.getInstance().getToken(),ply_sn,payment_method)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitOrderBean>() {

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
                    public void onNext(SubmitOrderBean getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.orderSubmitPlay(getInfo);
                        L.i(new Gson().toJson(getInfo));
                    }
                });
        addSubscription(s);
    }

}

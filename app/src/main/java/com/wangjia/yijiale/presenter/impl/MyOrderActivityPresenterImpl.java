package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.MyOrder;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.iview.MyOrderActivityView;
import com.wangjia.yijiale.presenter.MyOrderActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class MyOrderActivityPresenterImpl extends BasePresenterImpl implements MyOrderActivityPresenter {

    private MyOrderActivityView myOrderActivityView;
    private CacheUtil mCacheUtil;

    public MyOrderActivityPresenterImpl(MyOrderActivityView myOrderActivityView, Context context) {
        if (myOrderActivityView==null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.myOrderActivityView = myOrderActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(String token, int status,int page_now) {
//        myOrderActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().myOrder(token, status,page_now,100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyOrder>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myOrderActivityView.hidProgressDialog();
                        myOrderActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(MyOrder getInfo) {
                        myOrderActivityView.hidProgressDialog();
                        myOrderActivityView.getData( getInfo);
                        L.i(new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


//订单操作  取消  付款，评价，确认收货
    @Override
    public void orderOperte(String token, String order_id, String state_type) {
//        myOrderActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().orderOperte(token,order_id,state_type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyOrder>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myOrderActivityView.hidProgressDialog();
                        myOrderActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(MyOrder getInfo) {
                        myOrderActivityView.hidProgressDialog();
                        myOrderActivityView.orderOperte( getInfo);
                        L.i(new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    //订单确认支付
    @Override
    public void orderSubmitPlay(String ply_sn,String payment_method,String pd_pay) {
//        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .orderSubmitPlay(YiApplication.getInstance().getToken(),ply_sn,payment_method ,pd_pay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitOrderBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myOrderActivityView.hidProgressDialog();
                        myOrderActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(SubmitOrderBean getInfo) {
                        myOrderActivityView.hidProgressDialog();
                        myOrderActivityView.orderSubmitPlay(getInfo);
                        L.i(new Gson().toJson(getInfo));
                    }
                });
        addSubscription(s);
    }
}

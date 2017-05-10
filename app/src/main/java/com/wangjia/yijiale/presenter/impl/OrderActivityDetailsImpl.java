package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.OrderDetails;
import com.wangjia.yijiale.iview.OrderDetailsActivityView;
import com.wangjia.yijiale.presenter.DetailsOrderDetailsPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 * 订单详情的实现jiegou
 */
public class OrderActivityDetailsImpl extends BasePresenterImpl implements DetailsOrderDetailsPresenter {

    private OrderDetailsActivityView orderDetailsActivityView;
    private CacheUtil mCacheUtil;

    public OrderActivityDetailsImpl(OrderDetailsActivityView orderDetailsActivityView, Context context) {
        if (orderDetailsActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.orderDetailsActivityView = orderDetailsActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(String token, int order_id) {
//        myOrderActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().myOrderDetails(token, order_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderDetails>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        orderDetailsActivityView.hidProgressDialog();
                        orderDetailsActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(OrderDetails getInfo) {
                        orderDetailsActivityView.hidProgressDialog();
                        orderDetailsActivityView.getData(getInfo);
                        L.i(new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    //评价订单
    @Override
    public void commentOrder(String token, int order_id, int store_desccredit, int store_servicecredit, int store_deliverycredit, int goods) {
//        myOrderActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().commentOrder(token, order_id, store_desccredit,
                store_servicecredit, store_deliverycredit, goods)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        orderDetailsActivityView.hidProgressDialog();
                        orderDetailsActivityView.showError(e.getMessage());
                        Log.i("评价订单:", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean bean) {
                        orderDetailsActivityView.hidProgressDialog();
                        orderDetailsActivityView.commentOrder(bean);
                        L.i("评价订单:",new Gson().toJson(bean));
                    }
                });
        addSubscription(s);
    }
}

package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.AddressManageList;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.entity.SubmitSteOneBean;
import com.wangjia.yijiale.entity.SubmitSteTwoBean;
import com.wangjia.yijiale.iview.ConfirmOrderActivityView;
import com.wangjia.yijiale.presenter.ConfirmOrderActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class ConfirmOrderActivityPresenterImpl extends BasePresenterImpl implements ConfirmOrderActivityPresenter {

    private ConfirmOrderActivityView view;
    private CacheUtil mCacheUtil;

    public ConfirmOrderActivityPresenterImpl(ConfirmOrderActivityView view, Context context) {
        if (view == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.view = view;
        mCacheUtil = CacheUtil.get(context);
    }


    //获取购物车列表
    @Override
    public void getData(String token, String store_id) {
//        view.showProgressDialog();
        Log.i("获取商品详情购物车列表,请求参数:", "store_id:" + store_id + "--token:" + token);
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .cart_list_old(token, store_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Cart>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("TAG", "购物车列表:" + e.getMessage());
                    }

                    @Override
                    public void onNext(Cart getInfo) {
                        view.getData(getInfo);
                        view.hidProgressDialog();
                        L.i("购物车列表:" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    @Override
    public void getDefaultAddress(String token) {
//        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().address_list(token, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddressManageList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("TAG", "获取默认收货地址" + e.getMessage());
                    }

                    @Override
                    public void onNext(AddressManageList getInfo) {
                        view.hidProgressDialog();
                        view.getDefaultAddress(getInfo);
                        L.i("获取默认收货地址" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    //获取确认订单数据
    @Override
    public void submitOrderForOnce(String token, String store_id, int ifcart, String cart_id, String address_id) {
//        view.showProgressDialog();
        Log.i("获取确认订单页面数据,请求参数", "token:" + token + "--store_id:" + store_id + "--ifcart:" + ifcart +
                "--cart_id:" + cart_id + "--address_id" + address_id);
        Subscription s = BaseNoCacheRequest.getBaseApi().submitOrderForOnce(token, store_id, ifcart, cart_id, address_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitSteOneBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("TAG", "获取确认订单页面数据" + e.getMessage());
                    }

                    @Override
                    public void onNext(SubmitSteOneBean getInfo) {
                        view.hidProgressDialog();
                        view.submitOrderForOnce(getInfo);
                        L.i("获取确认订单页面数据" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    //提交订单
    @Override
    public void submitOrderForTwo(String token, String goods_id, int ifcart, String cart_id, String address_id, String pay_message,
                                  String pay_name, String vat_hash,
                                  String allow_offpay, String offpay_hash,
                                  String offpay_hash_batch, String buy_city_id) {
//        view.showProgressDialog();
        Log.i("提交订单,请求参数", "token:" + token + "--goods_id:" + goods_id + "--pay_message:" + pay_message +
                "--cart_id:" + cart_id + "--address_id" + address_id);
        Subscription s = BaseNoCacheRequest.getBaseApi().submitOrderForTwo(token, goods_id, ifcart, cart_id, address_id, pay_message,
                pay_name, vat_hash,
                allow_offpay, offpay_hash,
                offpay_hash_batch, buy_city_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitSteTwoBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("提交订单", e.getMessage());
                    }

                    @Override
                    public void onNext(SubmitSteTwoBean getInfo) {
                        view.hidProgressDialog();
                        view.submitOrderForTwo(getInfo);
                        L.i("提交订单", new Gson().toJson(getInfo));
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

package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.GoodsDetailInfo;
import com.wangjia.yijiale.iview.GoodsDetailActivityView;
import com.wangjia.yijiale.presenter.GoodsDetailActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class GoodsDetailActivityPresenterImpl extends BasePresenterImpl implements GoodsDetailActivityPresenter {

    private GoodsDetailActivityView view;
    private CacheUtil mCacheUtil;

    public GoodsDetailActivityPresenterImpl(GoodsDetailActivityView view, Context context) {
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

    //获取商品详情
    @Override
    public void getGoodsData(String token, String goods_id) {
//        view.showProgressDialog();
        Log.i("获取商品详情,请求参数:", "goods_id:" + goods_id + "--token:" + token);
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .getGoodsDetail(token, goods_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsDetailInfo>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("TAG", "获取商品详情" + e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsDetailInfo getInfo) {
                        view.getGoodsData(getInfo);
                        view.hidProgressDialog();
                        L.i("获取商品详情:" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    //设置收藏
    @Override
    public void setGoodsCollect(String goods_id, int is_favorate) {
//        view.showProgressDialog();
        Log.i("商品收藏,请求参数:", "goods_id:" + goods_id + "--is_favorate:" + is_favorate + "--token" + YiApplication.getInstance().getToken());
        if (is_favorate == 0) {
            Subscription s = BaseNoCacheRequest.getBaseApi()
                    .addGoodsCollect(goods_id, YiApplication.getInstance().getToken())
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
                            Log.i("TAG", "设置商品收藏:" + e.getMessage());
                        }

                        @Override
                        public void onNext(BaseBean getInfo) {
                            view.hidProgressDialog();
                            view.collectGoodsResult(getInfo);
                            L.i("设置商品收藏:" + new Gson().toJson(getInfo));
                            //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                        }
                    });
            addSubscription(s);
        } else if (is_favorate == 1) {
            Subscription s = BaseNoCacheRequest.getBaseApi()
                    .delGoodsCollect(goods_id, YiApplication.getInstance().getToken())
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
                            Log.i("TAG", "取消商品收藏:" + e.getMessage());
                        }

                        @Override
                        public void onNext(BaseBean getInfo) {
                            view.hidProgressDialog();
                            view.collectGoodsResult(getInfo);
                            L.i("取消商品收藏:" + new Gson().toJson(getInfo));
                        }
                    });
            addSubscription(s);
        }
    }


    //改变购物车数量
    @Override
    public void changeCartNum(String goods_id, String quantity, String token) {
//        view.showProgressDialog();
        Log.i("改变购物车数量，请求参数", "goods_id:" + goods_id + "--quantity:" + quantity);
        Subscription s = BaseNoCacheRequest.getBaseApi()
//                .get_update_info_by_edit_quantity(cart_id, quantity, token)
                .update_cart_info_in_goods(goods_id, quantity, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangeNum>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("购物车数量：", e.getMessage());
                    }

                    @Override
                    public void onNext(ChangeNum getInfo) {
                        view.hidProgressDialog();
                        view.changeCartNum(getInfo);
                        L.i("购物车数量", new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    @Override
    public void changeNum(String goods_id, String quantity, String token) {
//        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi()
                .update_cart_info_in_goods(goods_id, quantity, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangeNum>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("TAG", "de商品列表增减购物车数量：" + e.getMessage());
                    }

                    @Override
                    public void onNext(ChangeNum getInfo) {
                        view.hidProgressDialog();
                        view.changeNum(getInfo);
                        L.i("de商品列表增减购物车数量：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    //清空购物车
    @Override
    public void clearShopping(String store_id, String token) {
        Log.i("清空购物车,请求参数:", "store_id:" + store_id + "--token:" + token);
//        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi()
                .clearShopping(store_id, token)
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
                        Log.i("清空购物车：", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean getInfo) {
                        view.hidProgressDialog();
                        view.clearShopping(getInfo);
                        L.i("清空购物车：" + new Gson().toJson(getInfo));
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

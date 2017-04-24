package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.StoreDetailInfo;
import com.wangjia.yijiale.iview.DetailActivityView;
import com.wangjia.yijiale.presenter.DetailActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class DetailActivityPresenterImpl extends BasePresenterImpl implements DetailActivityPresenter {

    private DetailActivityView detailActivityView;
    private CacheUtil mCacheUtil;

    public DetailActivityPresenterImpl(DetailActivityView detailActivityView, Context context) {
        if (detailActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.detailActivityView = detailActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    //获取购物车列表
    @Override
    public void getData(String token, String store_id) {
//        detailActivityView.showProgressDialog();
        Log.i("获取购物车列表,请求参数:", "token:" + token + "--store_id:" + store_id);
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
                        detailActivityView.hidProgressDialog();
                        detailActivityView.showError(e.getMessage());
                        Log.i("TAG", "获取购物车列表:" + e.getMessage());
                    }

                    @Override
                    public void onNext(Cart getInfo) {
                        detailActivityView.getData(getInfo);
                        detailActivityView.hidProgressDialog();
                        L.i("获取购物车列表:" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    //获取店铺详情
    @Override
    public void getStoreData(String store_id) {
//        detailActivityView.showProgressDialog();
        Log.i("获取店铺详情,请求参数:", "store_id:" + store_id);
        Subscription s = BaseNoCacheRequest.getBaseApi()
                .store_contactinfo(store_id, YiApplication.getInstance().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreDetailInfo>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        detailActivityView.hidProgressDialog();
                        detailActivityView.showError(e.getMessage());
                        Log.i("TAG", "获取店铺详情" + e.getMessage());
                    }

                    @Override
                    public void onNext(StoreDetailInfo getInfo) {
                        detailActivityView.hidProgressDialog();
                        detailActivityView.getStroeData(getInfo);
                        L.i("获取店铺详情:" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    //设置收藏
    @Override
    public void setCollect(String store_id, int is_favorate) {
        detailActivityView.showProgressDialog();
        if (is_favorate == 0) {
            Subscription s = BaseNoCacheRequest.getBaseApi()
                    .addStoreCollect(store_id, YiApplication.getInstance().getToken())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseBean>() {

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            detailActivityView.hidProgressDialog();
                            detailActivityView.showError(e.getMessage());
                            Log.i("TAG", "设置店铺收藏：" + e.getMessage());
                        }

                        @Override
                        public void onNext(BaseBean getInfo) {
                            detailActivityView.hidProgressDialog();
                            detailActivityView.collectResult(getInfo);
                            L.i("设置店铺收藏：" + new Gson().toJson(getInfo));
                            //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                        }
                    });
            addSubscription(s);
        } else if (is_favorate == 1) {
            Subscription s = BaseNoCacheRequest.getBaseApi()
                    .delStoreCollect(store_id, YiApplication.getInstance().getToken())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseBean>() {

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            detailActivityView.hidProgressDialog();
                            detailActivityView.showError(e.getMessage());
                            Log.i("TAG", "取消店铺收藏：" + e.getMessage());
                        }

                        @Override
                        public void onNext(BaseBean getInfo) {
                            detailActivityView.hidProgressDialog();
                            detailActivityView.collectResult(getInfo);
                            L.i("取消店铺收藏：" + new Gson().toJson(getInfo));
                            //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                        }
                    });
            addSubscription(s);
        }
    }


    //改变购物车数量
    @Override
    public void changeCartNum(String goods_id, String quantity, String token) {
//        detailActivityView.showProgressDialog();
        Log.i("改变购物车数量,请求参数:", "goods_id:" + goods_id + "--quantity:" + quantity + "--token:" + token);
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
                        detailActivityView.hidProgressDialog();
                        detailActivityView.showError(e.getMessage());
                        Log.i("TAG", "改变购物车数量:" + e.getMessage());
                    }

                    @Override
                    public void onNext(ChangeNum getInfo) {
                        detailActivityView.hidProgressDialog();
                        detailActivityView.changeCartNum(getInfo);
                        L.i("改变购物车数量:" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    //清空购物车
    @Override
    public void clearShopping(String store_id, String token) {
        Log.i("清空购物车,请求参数:", "store_id:" + store_id + "--token:" + token);
        detailActivityView.showProgressDialog();
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
                        detailActivityView.hidProgressDialog();
                        detailActivityView.showError(e.getMessage());
                        Log.i("清空购物车：", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean getInfo) {
                        detailActivityView.hidProgressDialog();
                        detailActivityView.clearShopping(getInfo);
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

package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.CommentListBean;
import com.wangjia.yijiale.entity.StoreGoodsList;
import com.wangjia.yijiale.iview.ProductListFragmentView;
import com.wangjia.yijiale.presenter.ProductListFragmentPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class ProductListFragmentPresenterImpl extends BasePresenterImpl implements ProductListFragmentPresenter {

    private ProductListFragmentView productListFragmentView;
    private CacheUtil mCacheUtil;

    public ProductListFragmentPresenterImpl(ProductListFragmentView productListFragmentView, Context context) {
        if (productListFragmentView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.productListFragmentView = productListFragmentView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(String store_id, String now_page) {
        productListFragmentView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .store_goods_list(store_id, now_page, YiApplication.getInstance().getToken(),50)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreGoodsList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        productListFragmentView.hidProgressDialog();
                        productListFragmentView.showError(e.getMessage());
                        Log.i("TAG", "获取商品列表：" + e.getMessage());
                    }

                    @Override
                    public void onNext(StoreGoodsList getInfo) {
                        productListFragmentView.hidProgressDialog();
                        productListFragmentView.getData(getInfo);
                        L.i("获取商品列表：" + new Gson().toJson(getInfo));
                    }
                });
        addSubscription(s);
    }

    @Override
    public void changeNum(String goods_id, String quantity, String token) {
        productListFragmentView.showProgressDialog();
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
                        productListFragmentView.hidProgressDialog();
                        productListFragmentView.showError(e.getMessage());
                        Log.i("TAG", "商品列表增减购物车数量：" + e.getMessage());
                    }

                    @Override
                    public void onNext(ChangeNum getInfo) {
                        productListFragmentView.hidProgressDialog();
                        productListFragmentView.changeNum(getInfo);
                        L.i("商品列表增减购物车数量：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    @Override
    public void getStoreComment(int store_id, int now_page, int now_sum) {
        productListFragmentView.showProgressDialog();
        L.i("店铺评价列表,请求参数：", "store_id" + store_id + "--now_page" + now_page + "--now_sum" + now_sum);
        Subscription s = BaseNoCacheRequest.getBaseApi()
                .getStoreComment(store_id, now_page, now_sum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentListBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        productListFragmentView.hidProgressDialog();
                        productListFragmentView.showError(e.getMessage());
                        Log.i("店铺评价列表：", e.getMessage());
                    }

                    @Override
                    public void onNext(CommentListBean getInfo) {
                        productListFragmentView.hidProgressDialog();
                        productListFragmentView.getStoreCommenrt(getInfo);
                        L.i("店铺评价列表：" + new Gson().toJson(getInfo));
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

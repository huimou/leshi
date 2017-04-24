package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.CollectionList;
import com.wangjia.yijiale.iview.MyCollectionActivityView;
import com.wangjia.yijiale.presenter.MyCollectionActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class MyCollectionActivityPresenterImpl extends BasePresenterImpl implements MyCollectionActivityPresenter {

    private MyCollectionActivityView myCollectionActivityView;
    private CacheUtil mCacheUtil;

    public MyCollectionActivityPresenterImpl(MyCollectionActivityView myCollectionActivityView, Context context) {
        if (myCollectionActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.myCollectionActivityView = myCollectionActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getCollectionList(String token, String now_page) {
        myCollectionActivityView.showProgressDialog();
        Log.i( "商品收藏列表,请求参数", "now_page:"+now_page+"--token:"+ YiApplication.getInstance().getToken());
        Subscription s = BaseNoCacheRequest.getBaseApi().CollectionList(token, now_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectionList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myCollectionActivityView.hidProgressDialog();
                        myCollectionActivityView.showError(e.getMessage());
                        Log.i( "商品收藏列表", e.getMessage());
                    }

                    @Override
                    public void onNext(CollectionList getInfo) {
                        myCollectionActivityView.hidProgressDialog();
                        myCollectionActivityView.getData(getInfo);
                        L.i("商品收藏列表", new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    //设置收藏
    @Override
    public void setGoodsCollect(String goods_id) {
//        view.showProgressDialog();
        Log.i("商品收藏,请求参数:", "goods_id:" + goods_id + "--token"+YiApplication.getInstance().getToken());

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
                            myCollectionActivityView.hidProgressDialog();
                            myCollectionActivityView.showError(e.getMessage());
                            Log.i("TAG", "取消商品收藏:"+e.getMessage());
                        }

                        @Override
                        public void onNext(BaseBean getInfo) {
                            myCollectionActivityView.hidProgressDialog();
                            myCollectionActivityView.collectGoodsResult(getInfo);
                            L.i("取消商品收藏:"+new Gson().toJson(getInfo));
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

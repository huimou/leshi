package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.StoreCollectionList;
import com.wangjia.yijiale.iview.MyStoreCollectionActivityView;
import com.wangjia.yijiale.presenter.MyStoreCollectionActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class MyStoreCollectionActivityPresenterImpl extends BasePresenterImpl implements MyStoreCollectionActivityPresenter {

    private MyStoreCollectionActivityView myStoreCollectionActivityView;
    private CacheUtil mCacheUtil;

    public MyStoreCollectionActivityPresenterImpl(MyStoreCollectionActivityView myStoreCollectionActivityView, Context context) {
        if (myStoreCollectionActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.myStoreCollectionActivityView = myStoreCollectionActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getStoreCollectionList(String token,  String now_page) {
        myStoreCollectionActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().StoreCollectionList(token,  now_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreCollectionList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myStoreCollectionActivityView.hidProgressDialog();
                        myStoreCollectionActivityView.showError(e.getMessage());
                        Log.i("TAG", "商家收藏列表：" + e.getMessage());
                    }

                    @Override
                    public void onNext(StoreCollectionList getInfo) {
                        myStoreCollectionActivityView.hidProgressDialog();
                        myStoreCollectionActivityView.getData(getInfo);
                        L.i("商家收藏列表：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    //设置收藏
    @Override
    public void setCollect(String store_id) {
        myStoreCollectionActivityView.showProgressDialog();

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
                            myStoreCollectionActivityView.hidProgressDialog();
                            myStoreCollectionActivityView.showError(e.getMessage());
                            Log.i("TAG", "删除店铺收藏：" + e.getMessage());
                        }

                        @Override
                        public void onNext(BaseBean getInfo) {
                            myStoreCollectionActivityView.hidProgressDialog();
                            myStoreCollectionActivityView.collectResult(getInfo);
                            L.i("删除店铺收藏：" + new Gson().toJson(getInfo));
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

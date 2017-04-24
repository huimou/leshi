package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.AreaList;
import com.wangjia.yijiale.iview.AddAddressChooseActivityView;
import com.wangjia.yijiale.presenter.AddAddressChooseActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class AddAddressChooseActivityPresenterImpl extends BasePresenterImpl implements AddAddressChooseActivityPresenter {

    private AddAddressChooseActivityView addAddressChooseActivityView;
    private CacheUtil mCacheUtil;

    public AddAddressChooseActivityPresenterImpl(AddAddressChooseActivityView addAddressChooseActivityView, Context context) {
        if (addAddressChooseActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.addAddressChooseActivityView = addAddressChooseActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getShengList(String area_id) {
//        addAddressChooseActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .area_list(area_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        addAddressChooseActivityView.hidProgressDialog();
                        addAddressChooseActivityView.showError(e.getMessage());
                        Log.i("TAG", "获取省份：" + e.getMessage());
                    }

                    @Override
                    public void onNext(AreaList getInfo) {
                        addAddressChooseActivityView.hidProgressDialog();
                        addAddressChooseActivityView.getShengList((AreaList) getInfo);
                        L.i("获取省份：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    @Override
    public void getShiList(String area_id) {
//        addAddressChooseActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().area_list(area_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        addAddressChooseActivityView.hidProgressDialog();
                        addAddressChooseActivityView.showError(e.getMessage());
                        Log.i("TAG","获取城市列表："+ e.getMessage());
                    }

                    @Override
                    public void onNext(AreaList getInfo) {
                        addAddressChooseActivityView.hidProgressDialog();
                        addAddressChooseActivityView.getShiList((AreaList) getInfo);
                        L.i("获取城市列表："+new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    @Override
    public void getQuList(String area_id) {
//        addAddressChooseActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().area_list(area_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        addAddressChooseActivityView.hidProgressDialog();
                        addAddressChooseActivityView.showError(e.getMessage());
                        Log.i("TAG", "获取区县列表："+e.getMessage());
                    }

                    @Override
                    public void onNext(AreaList getInfo) {
                        addAddressChooseActivityView.hidProgressDialog();
                        addAddressChooseActivityView.getQuList((AreaList) getInfo);
                        L.i("获取区县列表："+new Gson().toJson(getInfo));
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

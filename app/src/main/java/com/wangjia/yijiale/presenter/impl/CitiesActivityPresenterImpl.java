package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.Login;
import com.wangjia.yijiale.iview.CitiesActivityView;
import com.wangjia.yijiale.presenter.LoginActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class CitiesActivityPresenterImpl extends BasePresenterImpl implements LoginActivityPresenter {

    private CitiesActivityView citiesActivityView;
    private CacheUtil mCacheUtil;

    public CitiesActivityPresenterImpl(CitiesActivityView citiesActivityView, Context context) {
        if (citiesActivityView==null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.citiesActivityView = citiesActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(String member_mobile, String member_passwd) {
        citiesActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .login(member_mobile, member_passwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Login>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        citiesActivityView.hidProgressDialog();
                        citiesActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(Login getInfo) {
                        citiesActivityView.hidProgressDialog();
                        citiesActivityView.getData(getInfo);
                        L.i(new Gson().toJson(getInfo));
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

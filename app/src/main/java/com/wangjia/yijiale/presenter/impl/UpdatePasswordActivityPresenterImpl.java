package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.CodeBean;
import com.wangjia.yijiale.entity.UpdatePasswordBean;
import com.wangjia.yijiale.iview.UpdatePasswordActivityView;
import com.wangjia.yijiale.presenter.UpdatePasswordActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class UpdatePasswordActivityPresenterImpl extends BasePresenterImpl implements UpdatePasswordActivityPresenter {

    private UpdatePasswordActivityView registerActivityView;
    private CacheUtil mCacheUtil;

    public UpdatePasswordActivityPresenterImpl(UpdatePasswordActivityView registerActivityView, Context context) {
        if (registerActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.registerActivityView = registerActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(String member_mobile, String member_password,String verific) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .update_password(member_mobile, member_password,verific, YiApplication.getInstance().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdatePasswordBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(UpdatePasswordBean getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.getData(getInfo);
                        L.i(new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    @Override
    public void getCode(String mobile, String type) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .get_code(mobile, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CodeBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(CodeBean getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.getCode(getInfo);
                        L.i(new Gson().toJson(getInfo));
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

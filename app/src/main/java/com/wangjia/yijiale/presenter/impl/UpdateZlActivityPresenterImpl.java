package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.Login;
import com.wangjia.yijiale.iview.UpdateZlActivityView;
import com.wangjia.yijiale.presenter.UpdateZlActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import java.io.File;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class UpdateZlActivityPresenterImpl extends BasePresenterImpl implements UpdateZlActivityPresenter {

    private CacheUtil mCacheUtil;
    private UpdateZlActivityView updateZlActivityView;

    public UpdateZlActivityPresenterImpl(UpdateZlActivityView updateZlActivityView, Context context) {
        if (updateZlActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.updateZlActivityView = updateZlActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(File member_avatar, int member_sex, int member_age, String member_idcard,String member_truename,String member_name) {
        updateZlActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .upMemberInFo(YiApplication.getInstance().getToken(),member_avatar, member_sex, member_age, member_idcard,member_truename,member_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Login>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        updateZlActivityView.hidProgressDialog();
                        updateZlActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(Login getInfo) {
                        updateZlActivityView.hidProgressDialog();
                        updateZlActivityView.getData(getInfo);
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

package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.AboutBean;
import com.wangjia.yijiale.entity.CodeBean;
import com.wangjia.yijiale.entity.Register;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.entity.ZhifuShiWuBean;
import com.wangjia.yijiale.iview.RegisterActivityView;
import com.wangjia.yijiale.presenter.RegisterActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class RegisterActivityPresenterImpl extends BasePresenterImpl implements RegisterActivityPresenter {

    private RegisterActivityView registerActivityView;
    private CacheUtil mCacheUtil;

    public RegisterActivityPresenterImpl(RegisterActivityView registerActivityView, Context context) {
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
                .register(member_mobile, member_password,verific)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Register>() {

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
                    public void onNext(Register getInfo) {
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

    //加载关于我们
    @Override
    public void getAbout() {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .get_about("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AboutBean>() {

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
                    public void onNext(AboutBean getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.getAbout(getInfo);
                        L.i(new Gson().toJson(getInfo));
                    }
                });
        addSubscription(s);
    }


    //会员充值界面显示
    @Override
    public void showVip() {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .showVip(YiApplication.getInstance().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShowVipBean>() {

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
                    public void onNext(ShowVipBean getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.showVip(getInfo);
                        L.i(new Gson().toJson(getInfo));
                    }
                });
        addSubscription(s);
    }

//实物订单支付页面
    @Override
    public void shiwuOrder(String ply_sn) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .shiwuOrder(YiApplication.getInstance().getToken(),ply_sn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhifuShiWuBean>() {

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
                    public void onNext(ZhifuShiWuBean getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.shiwuOrder(getInfo);
                        L.i(new Gson().toJson(getInfo));
                    }
                });
        addSubscription(s);
    }

    //订单确认支付
    @Override
    public void orderSubmitPlay(String ply_sn,String payment_method,String pd_pay) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .orderSubmitPlay(YiApplication.getInstance().getToken(),ply_sn,payment_method,pd_pay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitOrderBean>() {

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
                    public void onNext(SubmitOrderBean getInfo) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.orderSubmitPlay(getInfo);
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

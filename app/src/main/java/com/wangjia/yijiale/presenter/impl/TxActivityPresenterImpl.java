package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.VipSubmitBean;
import com.wangjia.yijiale.iview.TxActivityView;
import com.wangjia.yijiale.presenter.TxActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class TxActivityPresenterImpl extends BasePresenterImpl implements TxActivityPresenter {

    private TxActivityView registerActivityView;
    private CacheUtil mCacheUtil;

    public TxActivityPresenterImpl(TxActivityView registerActivityView, Context context) {
        if (registerActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.registerActivityView = registerActivityView;
        mCacheUtil = CacheUtil.get(context);
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

    //会员提交提现申请
    @Override
    public void vipTxApply(String token, String pdc_amount, String pdc_bank_name, String pdc_bank_no,
                           String pdc_bank_user, String pdc_bank_province, String pdc_bank_city) {
        registerActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .vipTxApply(token, pdc_amount, pdc_bank_name, pdc_bank_no, pdc_bank_user, pdc_bank_province, pdc_bank_city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VipSubmitBean>() {

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
                    public void onNext(VipSubmitBean bean) {
                        registerActivityView.hidProgressDialog();
                        registerActivityView.vipTxApply(bean);
                        L.i(new Gson().toJson(bean));
                    }
                });
        addSubscription(s);
    }


}

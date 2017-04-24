package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.AddressManageList;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.iview.AddressManageActivityView;
import com.wangjia.yijiale.presenter.AddressManageActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class AddressManageActivityPresenterImpl extends BasePresenterImpl implements AddressManageActivityPresenter {

    private AddressManageActivityView view;
    private CacheUtil mCacheUtil;

    public AddressManageActivityPresenterImpl(AddressManageActivityView view, Context context) {
        if (view == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.view = view;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(String token) {
//        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().address_list(token,0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddressManageList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("TAG", "获取收货地址列表" + e.getMessage());
                    }

                    @Override
                    public void onNext(AddressManageList getInfo) {
                        view.hidProgressDialog();
                        view.getData(getInfo);
                        L.i("获取收货地址列表" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    /**
     * 删除收货地址
     * @param token
     */
    @Override
    public void getDeleteAddress(String token,String address_id) {
//        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().getDeleteAddress(token,address_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("TAG", "删除收货地址" + e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean getInfo) {
                        view.hidProgressDialog();
                        view.getDeleteAddress(getInfo);
                        L.i("删除收货地址" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

//    @Override
//    public void getUpdateAddress(String token, String true_name, String city_id, String area_id,
//                                 String tel_phone, String is_default, String post_code, String address, String province_id) {
//
//    }






    /*@Override
    public void getGuokrHotFromCache(int offset) {
        if (mCacheUtil.getAsJSONObject(Config.FirstFragment + offset) != null) {
            GetVersion getVersion = new Gson().fromJson(mCacheUtil.getAsJSONObject(Config.FirstFragment + offset).toString(), GetVersion.class);
            firstFragmentView.getVersion((GetVersion) getVersion);
        }
    }*/
}

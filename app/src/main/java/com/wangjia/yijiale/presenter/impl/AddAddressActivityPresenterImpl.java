package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.AddAddress;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.iview.AddAddressActivityView;
import com.wangjia.yijiale.presenter.AddAddressActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class AddAddressActivityPresenterImpl extends BasePresenterImpl implements AddAddressActivityPresenter {

    private AddAddressActivityView view;
    private CacheUtil mCacheUtil;

    public AddAddressActivityPresenterImpl(AddAddressActivityView view, Context context) {
        if (view==null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.view = view;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(String token, String true_name, String city_id, String area_id, String tel_phone,
                        String is_default, String post_code, String address, String province_id) {
//        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().
                address_add(token, true_name, city_id, area_id, tel_phone, is_default, post_code, address, province_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddAddress>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidProgressDialog();
                        view.showError(e.getMessage());
                        Log.i("TAG", "保存收货地址："+e.getMessage());
                    }

                    @Override
                    public void onNext(AddAddress getInfo) {
                        view.hidProgressDialog();
                        view.getData(getInfo);
                        L.i("保存收货地址："+new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }


    /**
     * 更改收货地址
     * @param token
     */
    @Override
    public void getUpdateAddress(String token,String address_id, String true_name, String city_id, String area_id,
                                 String tel_phone, String is_default, String post_code, String address, String province_id) {
//        view.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().getUpdateAddress(token, address_id,true_name, city_id, area_id,
                tel_phone, is_default, post_code, address, province_id)
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
                        Log.i("TAG", "更改收货地址" + e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean getInfo) {
                        view.hidProgressDialog();
                        view.getUpdateAddress(getInfo);
                        L.i("更改收货地址" + new Gson().toJson(getInfo));
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

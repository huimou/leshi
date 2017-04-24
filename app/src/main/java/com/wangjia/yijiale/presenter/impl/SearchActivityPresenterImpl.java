package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.SearchShopBean;
import com.wangjia.yijiale.iview.SearchActivityView;
import com.wangjia.yijiale.presenter.SearchActivityPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class SearchActivityPresenterImpl extends BasePresenterImpl implements SearchActivityPresenter {

    private CacheUtil mCacheUtil;
    private SearchActivityView searchActivityView;

    public SearchActivityPresenterImpl(SearchActivityView searchActivityView, Context context) {
        if (searchActivityView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.searchActivityView = searchActivityView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getData(int now_page, String keyword, String area_info, String sc_id) {
        searchActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .search_shop(now_page, keyword, area_info, sc_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchShopBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        searchActivityView.hidProgressDialog();
                        searchActivityView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(SearchShopBean getInfo) {
                        searchActivityView.hidProgressDialog();
                        searchActivityView.getData(getInfo);
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

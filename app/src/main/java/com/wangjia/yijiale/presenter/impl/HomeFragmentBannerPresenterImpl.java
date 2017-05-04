package com.wangjia.yijiale.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjia.yijiale.api.BaseNoCacheRequest;
import com.wangjia.yijiale.entity.Banner;
import com.wangjia.yijiale.entity.Classify;
import com.wangjia.yijiale.entity.NearStoreList;
import com.wangjia.yijiale.entity.StoreAreaList;
import com.wangjia.yijiale.entity.StoreList;
import com.wangjia.yijiale.entity.StroeClassifyBean;
import com.wangjia.yijiale.iview.HomeFragmentBannerView;
import com.wangjia.yijiale.presenter.HomeFragmentBannerPresenter;
import com.wangjia.yijiale.utils.CacheUtil;
import com.wangjia.yijiale.utils.L;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin on 2016/10/26
 */
public class HomeFragmentBannerPresenterImpl extends BasePresenterImpl implements HomeFragmentBannerPresenter {

    private HomeFragmentBannerView homeFragmentBannerView;
    private CacheUtil mCacheUtil;

    public HomeFragmentBannerPresenterImpl(HomeFragmentBannerView homeFragmentBannerView, Context context) {
        if (homeFragmentBannerView == null)
            throw new IllegalArgumentException("firstFragment must not be null");
        this.homeFragmentBannerView = homeFragmentBannerView;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getBannerData() {
        homeFragmentBannerView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().home_banner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Banner>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(Banner getInfo) {
                        //homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.getBannerData((Banner) getInfo);
                        L.i("首页顶部滚动广告：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    //获取店铺 地址列表
    @Override
    public void getShengList() {
//        addAddressChooseActivityView.showProgressDialog();
        Subscription s = BaseNoCacheRequest
                .getBaseApi()
                .store_area_list("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreAreaList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.showError(e.getMessage());
                        Log.i("TAG", "获取店铺地址列表：" + e.getMessage());
                    }

                    @Override
                    public void onNext(StoreAreaList getInfo) {
                        homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.getStoreAareaList(getInfo);
                        L.i("获取店铺地址列表：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    //    @Override
//    public void getShiList(String area_id) {
////        addAddressChooseActivityView.showProgressDialog();
//        Subscription s = BaseNoCacheRequest.getBaseApi().area_list(area_id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<AreaList>() {
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        homeFragmentBannerView.hidProgressDialog();
//                        homeFragmentBannerView.showError(e.getMessage());
//                        Log.i("TAG", "获取城市列表：" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(AreaList getInfo) {
//                        homeFragmentBannerView.hidProgressDialog();
//                        homeFragmentBannerView.getShiList((AreaList) getInfo);
//                        L.i("获取城市列表：" + new Gson().toJson(getInfo));
//                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
//                    }
//                });
//        addSubscription(s);
//    }
//店铺分类
    @Override
    public void getStoreClassify() {
//        homeFragmentBannerView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().getStoreClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StroeClassifyBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(StroeClassifyBean getInfo) {
                        //homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.getStoreClassify(getInfo);
                        L.i("商家店铺分类：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    //首页中间广告
    @Override
    public void getCenterBannerData() {
        homeFragmentBannerView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().home_center_banner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Banner>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(Banner getInfo) {
                        //homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.getCenterBannerData(getInfo);
                        L.i("首页中间广告：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

//    首页店铺分类带图标
    @Override
    public void getClassifyData() {
//        homeFragmentBannerView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().get_goods_classify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Classify>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(Classify getInfo) {
                        //homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.getClassifyData((Classify) getInfo);
                        L.i("首页分类：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

//    商家列表
    @Override
    public void getStoreListData(String keyword, String area_info, String now_page) {
        //homeFragmentBannerView.showProgressDialog();
        Subscription s = BaseNoCacheRequest.getBaseApi().storeList(keyword, area_info, now_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.showError(e.getMessage());
                        Log.i("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(StoreList getInfo) {
                        homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.getStoreListData(getInfo);
                        L.i("首页店铺列表：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

    //附近店铺
    @Override
    public void nearStoreList(String lng, String lat, int sort, int page_size, String sc_id, String child_sc_id,
                              int page_now, String province, String city) {
        //homeFragmentBannerView.showProgressDialog();
        Log.e("附近店铺列表,请求参数：", "lng:" + lng + "--lat:" + lat + "--sort:" + sort + "--page_size:" + page_size + "--sc_id:" +
                sc_id + "--child_sc_id:" + child_sc_id + "--page_now:" + page_now + "--province:" + province + "--city:" + city);
        Subscription s = BaseNoCacheRequest.getBaseApi().nearStoreList(lng, lat, sort, page_size, sc_id, child_sc_id, page_now, province, city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearStoreList>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.showError(e.getMessage());
                        Log.i("TAG", "附近店铺列表：" + e.getMessage());
                    }

                    @Override
                    public void onNext(NearStoreList getInfo) {
                        homeFragmentBannerView.hidProgressDialog();
                        homeFragmentBannerView.getNearStoreListData(getInfo);
                        L.i("附近店铺列表：" + new Gson().toJson(getInfo));
                        //mCacheUtil.put(Config.FirstFragment, new Gson().toJson(getVersion));
                    }
                });
        addSubscription(s);
    }

}

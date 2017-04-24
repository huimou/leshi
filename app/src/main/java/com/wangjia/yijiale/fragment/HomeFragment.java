package com.wangjia.yijiale.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.activity.DetailActivity;
import com.wangjia.yijiale.activity.SearchActivity;
import com.wangjia.yijiale.activity.StoreListActivity;
import com.wangjia.yijiale.adapter.HomeDividerViewAdapter;
import com.wangjia.yijiale.adapter.HomeStoreViewAdapter;
import com.wangjia.yijiale.adapter.NetworkImageHolderView;
import com.wangjia.yijiale.entity.Banner;
import com.wangjia.yijiale.entity.Classify;
import com.wangjia.yijiale.entity.NearStoreList;
import com.wangjia.yijiale.entity.StoreAreaList;
import com.wangjia.yijiale.entity.StoreList;
import com.wangjia.yijiale.entity.StroeClassifyBean;
import com.wangjia.yijiale.event.HomeFragmentEvent;
import com.wangjia.yijiale.iview.HomeFragmentBannerView;
import com.wangjia.yijiale.location.CitiesActivity;
import com.wangjia.yijiale.presenter.HomeFragmentBannerPresenter;
import com.wangjia.yijiale.presenter.impl.HomeFragmentBannerPresenterImpl;
import com.wangjia.yijiale.scan.MipcaActivityCapture;
import com.wangjia.yijiale.utils.HttpUtils;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.views.CustomProgress;
import com.wangjia.yijiale.views.FullyGridLayoutManager;
import com.wangjia.yijiale.views.FullyLinearLayoutManager;
import com.wangjia.yijiale.views.NoScrollRecycleView;
import com.wangjia.yijiale.views.RecycleScrollview;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by kevin on 2016/10/26
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, HomeFragmentBannerView {
    @Bind(R.id.scrollView)
    RecycleScrollview scrollView;
    @Bind(R.id.titleBar)
    LinearLayout titleBar;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.home_divider_recyclerview)
    NoScrollRecycleView homeDividerRecyclerview;
    @Bind(R.id.home_store_recyclerview)
    RecyclerView homeStoreRecyclerview;
    @Bind(R.id.cycleView)
    ConvenientBanner cycleView;
    @Bind(R.id.location_name)
    TextView location_name;
    @Bind(R.id.location)
    LinearLayout location;
    @Bind(R.id.home_search)
    ImageView homeSearch;
    @Bind(R.id.scan)
    ImageView scan;
    @Bind(R.id.home_center_ll)
    ImageView homeCenterLl;
    //private BaseViewAdapter baseViewAdapter;
    private HomeStoreViewAdapter homeStoreViewAdapter;
    private HomeDividerViewAdapter homeDividerViewAdapter;
    private Intent tIntent;
    private int mLastY;
    private int mCount = 1;
    private HomeFragmentBannerPresenter homeFragmentBannerPresenter;
    private static final int LOGIN_CODE = 1001;
    private Subscription subscribe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tIntent = new Intent();
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    public void initData() {
        homeFragmentBannerPresenter = new HomeFragmentBannerPresenterImpl(this, getActivity());
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light, android.R.color.white);
        scrollView.registerOnScrollViewAlph(new RecycleScrollview.OnScrollAlphListener() {
            @Override
            public void setAlph(float alph) {
                titleBar.setAlpha(alph);
            }

            @Override
            public void setFAlph() {
                titleBar.setAlpha(1);
            }
        });
        homeDividerViewAdapter = new HomeDividerViewAdapter(getActivity(), null);
        homeDividerRecyclerview.setAdapter(homeDividerViewAdapter);

        homeStoreViewAdapter = new HomeStoreViewAdapter(getActivity(), null);
        homeStoreRecyclerview.setAdapter(homeStoreViewAdapter);
    }

    public void initView() {
        setOrientation1();
        setOrientation2();

        cycleView.setPageIndicator(new int[]{R.drawable.shape_item_index_gray, R.drawable.shape_item_index_blue});
        cycleView.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        cycleView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                L.i("" + position);
            }
        });
        homeFragmentBannerPresenter.getBannerData();
        homeFragmentBannerPresenter.getCenterBannerData();

        subscribe = RxBus.getDefault().toObservable(HomeFragmentEvent.class).subscribe(new Action1<HomeFragmentEvent>() {
            @Override
            public void call(HomeFragmentEvent homeFragmentEvent) {
                location_name.setText(homeFragmentEvent.getName());
            }
        });
    }

    /**
     * 给轮播图设置图片路径
     */
    public void setLoopView(Banner model) {
        List<String> imgUrls = new ArrayList<>();
        for (int i = 0; i < model.getDatas().size(); i++) {
            imgUrls.add(model.getDatas().get(i).getImg_url());
        }
        //初始化商品图片轮播
        cycleView.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, imgUrls);
    }

    /**
     * 设置RecycleView布局方式
     */
    public void setOrientation1() {
        //设置布局管理
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        homeDividerRecyclerview.setLayoutManager(new FullyGridLayoutManager(getActivity(), 4));
        homeDividerRecyclerview.setItemAnimator(new DefaultItemAnimator());
        homeDividerViewAdapter.setmOnItemClickListener(new HomeDividerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                ToastUtils.showToast(getActivity(),position+"");
                tIntent = new Intent(getActivity(), StoreListActivity.class);
                startActivity(tIntent);

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    /**
     * 设置RecycleView布局方式
     */
    public void setOrientation2() {
        //设置布局管理
        LinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        homeStoreRecyclerview.setLayoutManager(linearLayoutManager);
//        homeStoreRecyclerview.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
//        homeStoreRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        homeStoreViewAdapter.setmOnItemClickListener(new HomeStoreViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, StoreList.DatasBean.StoreListBean bean) {
                tIntent = new Intent(getActivity(), DetailActivity.class);
                tIntent.putExtra("store_id",bean.getStore_id());
                startActivity(tIntent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onResume() {
        super.onResume();
        cycleView.startTurning(4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        cycleView.stopTurning();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        subscribe.unsubscribe();
    }

    @OnClick({R.id.location, R.id.scan, R.id.home_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.location:
                tIntent.setClass(getActivity(), CitiesActivity.class);
                startActivity(tIntent);
                break;
            case R.id.scan:
                checkPermission();
                break;
            case R.id.home_search:
                tIntent.setClass(getActivity(), SearchActivity.class);
                startActivity(tIntent);
                break;
        }
    }

    public void checkPermission() {
        final List<String> permissionsList = new ArrayList<>();
        permissionsList.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                permissionsList.add(Manifest.permission.CAMERA);
            }
            if (permissionsList.size() != 0) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        100);
            } else {
                tIntent.setClass(getActivity(), MipcaActivityCapture.class);
                startActivity(tIntent);
                //openCarmera();
                //已经不是第一次,已经有权限
                Log.e("test", "permissionsList.size()==0");
            }
        } else {
            tIntent.setClass(getActivity(), MipcaActivityCapture.class);
            startActivity(tIntent);
            //openCarmera();
        }
    }

    private int countn = 0;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        countn++;
        Log.e("test", "onRequestPermissionsResult:" + countn);
        if (requestCode == 100) {
            if (grantResults.length > 0 && ((getActivity().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) || (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) || (getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) || (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))) {
                tIntent.setClass(getActivity(), MipcaActivityCapture.class);
                startActivity(tIntent);
                //Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void getBannerData(Banner model) {
        setLoopView(model);
        homeFragmentBannerPresenter.getClassifyData();
    }

    @Override
    public void getStoreClassify(StroeClassifyBean model) {

    }

    @Override
    public void getCenterBannerData(Banner model) {
        Glide.with(getActivity()).load(HttpUtils.getImageUrl(model.getDatas().get(0).getAd_content())).error(R.mipmap.logo1).into(homeCenterLl);
    }

    @Override
    public void getClassifyData(Classify model) {
        homeDividerViewAdapter.setDatas(model);
        homeFragmentBannerPresenter.getStoreListData("", "", "1");
    }

    @Override
    public void getStoreListData(StoreList model) {
        homeStoreViewAdapter.setDatas(model);
        //ProgressFlag = false;
    }

    @Override
    public void getNearStoreListData(NearStoreList model) {

    }

    @Override
    public void getStoreAareaList(StoreAreaList model) {

    }

    @Override
    public void showProgressDialog() {
        if (this.isVisible()) {
            CustomProgress.showProgress(getActivity(), "获取数据中...", false, null);
        }
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        CustomProgress.dissmiss();
    }
}

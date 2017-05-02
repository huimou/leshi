package com.wangjia.yijiale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.adapter.StoreListViewAdapter;
import com.wangjia.yijiale.entity.Banner;
import com.wangjia.yijiale.entity.Classify;
import com.wangjia.yijiale.entity.NearStoreList;
import com.wangjia.yijiale.entity.StoreAreaList;
import com.wangjia.yijiale.entity.StoreList;
import com.wangjia.yijiale.entity.StroeClassifyBean;
import com.wangjia.yijiale.iview.HomeFragmentBannerView;
import com.wangjia.yijiale.menu.SelectMenuView;
import com.wangjia.yijiale.presenter.impl.HomeFragmentBannerPresenterImpl;
import com.wangjia.yijiale.recycle.PullLoadMoreRecyclerView;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.views.CustomProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin on 2016/10/26
 * 店铺列表
 */
public class StoreListActivity extends AppCompatActivity implements HomeFragmentBannerView {

    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    @Bind(R.id.selectMenuView)
    SelectMenuView selectMenuView;
    private Intent tIntent;
    private StoreListViewAdapter homeStoreViewAdapter;
    private int mCount = 1;
    private HomeFragmentBannerPresenterImpl homeFragmentBannerPresenter;
    private List<NearStoreList.DatasBean> store_list;
    private ArrayList<List<String>> mSubjectStoreAddressList;
    private ArrayList<List<String>> mSubjectDataList;
    private int sc_id;
    private int child_sc_id;
    private int sort = 1;
    private int page_size = 20;
    private int area_id;
    private int child_area_id;
    private StroeClassifyBean stroeClassifymodel;
    private String area_name = "";
    private String child_area_name = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_list_activity);
        tIntent = new Intent();
        ButterKnife.bind(this);
        initView();
        initData();

    }

    public void initData() {
        homeFragmentBannerPresenter = new HomeFragmentBannerPresenterImpl(this, this);
        homeFragmentBannerPresenter.getStoreClassify();
        homeFragmentBannerPresenter.getShengList();
        homeFragmentBannerPresenter.nearStoreList("", "", sort, page_size, String.valueOf(sc_id)
                , String.valueOf(child_sc_id), mCount, area_name, child_area_name);
//        homeFragmentBannerPresenter.getStoreListData("", "", "1");

//        homeFragmentBannerPresenter.getShengList();
//        homeFragmentBannerPresenter.getShiList();
    }

    /**
     * 设置RecycleView布局方式
     */
    public void setOrientation2() {
        //设置布局管理
//        LinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(StoreListActivity.this, LinearLayoutManager.VERTICAL, false);
//        mPullLoadMoreRecyclerView.setLayoutManager(linearLayoutManager);
//        mPullLoadMoreRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
//        mPullLoadMoreRecyclerView.addItemDecoration(new DividerItemDecoration(StoreListActivity.this, DividerItemDecoration.VERTICAL_LIST));
        homeStoreViewAdapter.setmOnItemClickListener(new StoreListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, NearStoreList.DatasBean bean) {
                tIntent = new Intent(StoreListActivity.this, DetailActivity.class);
                tIntent.putExtra("store_id", bean.getStore_id());
                startActivity(tIntent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("店铺列表")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);
        //设置分割线
        //mPullLoadMoreRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mPullLoadMoreRecyclerView.setGridLayout(1);
    }

    /**
     * 设置RecycleView布局方式
     */
//    private List<String> setList1() {
//        List<String> dataList = new ArrayList<>();
//        int start = 5 * (mCount - 1);
//        for (int i = start; i < 20 * 1; i++) {
//            dataList.add("Second" + i);
//        }
//        return dataList;
//    }
    @Override
    public void getBannerData(Banner model) {

    }


    @Override
    public void getStoreClassify(StroeClassifyBean model) {
        //商家店铺分类
        this.stroeClassifymodel = model;
        mSubjectDataList = new ArrayList<>();
        ArrayList<String> mGroupList = new ArrayList<>();
        for (StroeClassifyBean.DatasBean.ClassListBean classListBean : model.getDatas().getClass_list()) {
            mGroupList.add(classListBean.getSc_name());
            ArrayList<String> mPrimaryList = new ArrayList<>();
            for (int i = 0; classListBean.getSc_nav() != null && i < classListBean.getSc_nav().size(); i++) {
                mPrimaryList.add(classListBean.getSc_nav().get(i).getSc_name());
            }
            mSubjectDataList.add(mPrimaryList);
        }
        mSubjectDataList.add(0, mGroupList);
//        selectMenuView.setInitData(mSubjectDataList, mSubjectStoreAddressList);
//        selectMenuView.setOnMenuSelectDataChangedListener(new SelectMenuView.OnMenuSelectDataChangedListener() {
//            @Override
//            public void onSubjectChanged(String grade, String subjects) {
//                try {
//                    //一级分类ID
////                    sc_id = model.getDatas().getClass_list().get(Integer.parseInt(grade)).getSc_id();
////                    //二级分类ID
////                    if (model.getDatas().getClass_list().get(Integer.parseInt(grade)).getSc_nav() != null
////                            && model.getDatas().getClass_list().get(Integer.parseInt(grade)).getSc_nav().size() > 0) {
////                        child_sc_id = model.getDatas().getClass_list().get(Integer.parseInt(grade)).
////                                getSc_nav().get(Integer.parseInt(subjects)).getSc_id();
////                    } else {
////                        child_sc_id = 0;
////                    }
////                    ToastUtils.showToast(StoreListActivity.this,sc_id+"--"+child_sc_id);
////                    homeFragmentBannerPresenter.nearStoreList(sort, page_size,String.valueOf(sc_id),String.valueOf(child_sc_id), mCount);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onSortChanged(String sortType) {
//                //智能查找
//                sort = Integer.parseInt(sortType);
//                ToastUtils.showToast(StoreListActivity.this,sort);
////                homeFragmentBannerPresenter.nearStoreList(sort, page_size, String.valueOf(sc_id)
////                        , String.valueOf(child_sc_id), mCount);
//            }
//
//            @Override
//            public void onSelectedChanged(String gender, String classType) {
//
//            }
//
//            @Override
//            public void onViewClicked(View view) {
//
//            }
//
//            @Override
//            public void onSelectedDismissed(String gender, String classType) {
//
//            }
//        });

    }

    @Override
    public void getCenterBannerData(Banner model) {

    }

    @Override
    public void getClassifyData(Classify model) {

    }

    @Override
    public void getStoreListData(StoreList model) {

    }

    @Override
    public void getNearStoreListData(NearStoreList model) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            store_list = model.getDatas();
            homeStoreViewAdapter = new StoreListViewAdapter(this, store_list);
            mPullLoadMoreRecyclerView.setAdapter(homeStoreViewAdapter);
            setOrientation2();
            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

        }
    }

    @Override
    public void getStoreAareaList(final StoreAreaList model) {
        //商家店铺地址
        mSubjectStoreAddressList = new ArrayList<>();
        ArrayList<String> mGroupList = new ArrayList<>();
        for (StoreAreaList.DatasBean.AreaListBean areaListBean : model.getDatas().getArea_list()) {
            mGroupList.add(areaListBean.getArea_name());
            ArrayList<String> mPrimaryList = new ArrayList<>();
            for (int i = 0; areaListBean.getArea_ernav() != null && i < areaListBean.getArea_ernav().size(); i++) {
                mPrimaryList.add(areaListBean.getArea_ernav().get(i).getArea_name());
            }
            mSubjectStoreAddressList.add(mPrimaryList);
        }
        mSubjectStoreAddressList.add(0, mGroupList);
        selectMenuView.setInitData(mSubjectDataList, mSubjectStoreAddressList);
        selectMenuView.setOnMenuSelectDataChangedListener(new SelectMenuView.OnMenuSelectDataChangedListener() {
            @Override
            public void onSubjectChanged(String grade, String subjects) {
                //一级分类ID
                sc_id = stroeClassifymodel.getDatas().getClass_list().get(Integer.parseInt(grade)).getSc_id();
                //二级分类ID
                if (stroeClassifymodel.getDatas().getClass_list().get(Integer.parseInt(grade)).getSc_nav() != null
                        && stroeClassifymodel.getDatas().getClass_list().get(Integer.parseInt(grade)).getSc_nav().size() > 0) {
                    child_sc_id = stroeClassifymodel.getDatas().getClass_list().get(Integer.parseInt(grade)).
                            getSc_nav().get(Integer.parseInt(subjects)).getSc_id();
                } else {
                    child_sc_id = 0;
                }
//                ToastUtils.showToast(StoreListActivity.this, sc_id + "--" + child_sc_id);
                homeFragmentBannerPresenter.nearStoreList("", "", sort, page_size, String.valueOf(sc_id)
                        , String.valueOf(child_sc_id), mCount, area_name, child_area_name);
            }

            @Override
            public void onSortChanged(String sortType) {
                //智能查找
                sort = Integer.parseInt(sortType);
                homeFragmentBannerPresenter.nearStoreList("", "", sort, page_size, String.valueOf(sc_id)
                        , String.valueOf(child_sc_id), mCount, area_name, child_area_name);
//                ToastUtils.showToast(StoreListActivity.this, sort + "");
            }

            @Override
            public void onSelectedChanged(String gender, String classType) {
                //一级分类地址ID
//                area_id = model.getDatas().getArea_list().get(Integer.parseInt(gender)).getArea_id();
                area_name = model.getDatas().getArea_list().get(Integer.parseInt(gender)).getArea_name();
                //二级分类地址ID
                if (model.getDatas().getArea_list().get(Integer.parseInt(gender)).getArea_ernav() != null
                        && model.getDatas().getArea_list().get(Integer.parseInt(gender)).getArea_ernav().size() > 0) {
                    child_area_name = model.getDatas().getArea_list().get(Integer.parseInt(gender)).
                            getArea_ernav().get(Integer.parseInt(classType)).getArea_name();
                } else {
                    child_area_name = "";
                }
//                ToastUtils.showToast(StoreListActivity.this, area_name + "--" + child_area_name);
                homeFragmentBannerPresenter.nearStoreList("", "", sort, page_size, String.valueOf(sc_id)
                        , String.valueOf(child_sc_id), mCount, area_name, child_area_name);
            }

            @Override
            public void onViewClicked(View view) {

            }

            @Override
            public void onSelectedDismissed(String gender, String classType) {

            }
        });
    }

    @Override
    public void showProgressDialog() {
        if (!this.isFinishing()) {
            CustomProgress.showProgress(StoreListActivity.this, "获取数据中...", false, null);
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

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            mCount = 1;
            homeFragmentBannerPresenter.getStoreListData("", "", String.valueOf(mCount));
        }

        @Override
        public void onLoadMore() {
            mCount++;
            homeFragmentBannerPresenter.getStoreListData("", "", String.valueOf(mCount));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

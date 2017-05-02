package com.wangjia.yijiale.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.activity.DetailActivity;
import com.wangjia.yijiale.adapter.NearStoreViewAdapter;
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
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.views.CustomProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin on 2016/10/26
 * 附近商城
 */
public class NearFragment extends Fragment implements HomeFragmentBannerView {

    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private Intent tIntent;
    private NearStoreViewAdapter homeStoreViewAdapter;
    private int mCount = 1;
    private HomeFragmentBannerPresenterImpl homeFragmentBannerPresenter;
    @Bind(R.id.selectNearMenuView)
    SelectMenuView selectMenuView;
    private int sort =2;
    private int page_size =20;
    private int sc_id;
    private int child_sc_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near, container, false);
        tIntent = new Intent();
        ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }

    public void initData() {
        homeFragmentBannerPresenter = new HomeFragmentBannerPresenterImpl(this, getActivity());
        homeFragmentBannerPresenter.nearStoreList(YiApplication.getLongitude(), YiApplication.getLatitude()
                ,sort, page_size,"","", mCount,"","");
        homeFragmentBannerPresenter.getStoreClassify();

    }

    /**
     * 设置RecycleView布局方式
     */
    public void setOrientation2() {
        //设置布局管理
//        LinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        mPullLoadMoreRecyclerView.setLayoutManager(linearLayoutManager);
        try {
//            mPullLoadMoreRecyclerView.setItemAnimator(new DefaultItemAnimator());
            //设置分割线
//            mPullLoadMoreRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            homeStoreViewAdapter.setmOnItemClickListener(new NearStoreViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int postion, NearStoreList.DatasBean bean) {
                    tIntent = new Intent(getActivity(), DetailActivity.class);
//                    tIntent.putExtra("store_id", bean.getStore_id());
                    startActivity(tIntent);
                }

                @Override
                public void onItemLongClick(View view, int postion) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initView(View view) {
        new Titlebulder(view).setTitleName("附近店铺");
        selectMenuView.setHanddinAddress();
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(false);
        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
        //设置分割线
        //mPullLoadMoreRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mPullLoadMoreRecyclerView.setGridLayout(1);
    }


    @Override
    public void getBannerData(Banner model) {

    }

    @Override
    public void getStoreClassify(final StroeClassifyBean model) {

        //商家店铺分类
        ArrayList<List<String>> mSubjectDataList = new ArrayList<>();
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
        selectMenuView.setInitData(mSubjectDataList,null);
        selectMenuView.setOnMenuSelectDataChangedListener(new SelectMenuView.OnMenuSelectDataChangedListener() {
            @Override
            public void onSubjectChanged(String grade, String subjects) {
                try {
                    //一级分类ID
                    sc_id = model.getDatas().getClass_list().get(Integer.parseInt(grade)).getSc_id();
                    //二级分类ID
                    if(model.getDatas().getClass_list().get(Integer.parseInt(grade)).getSc_nav()!=null
                    && model.getDatas().getClass_list().get(Integer.parseInt(grade)).getSc_nav().size()>0) {
                        child_sc_id = model.getDatas().getClass_list().get(Integer.parseInt(grade)).
                                getSc_nav().get(Integer.parseInt(subjects)).getSc_id();
                    }else{
                        child_sc_id = 0;
                    }
//                    ToastUtils.showToast(getActivity(),sc_id+"--"+child_sc_id);
                    homeFragmentBannerPresenter.nearStoreList(YiApplication.getLongitude(), YiApplication.getLatitude()
                            ,sort, page_size,String.valueOf(sc_id),String.valueOf(child_sc_id), mCount,"","");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSortChanged(String sortType) {
                    //智能查找
                sort = Integer.parseInt(sortType);
//                ToastUtils.showToast(getActivity(),sort+"--");
                homeFragmentBannerPresenter.nearStoreList(YiApplication.getLongitude(), YiApplication.getLatitude()
                        ,sort, page_size,String.valueOf(sc_id)
                        ,String.valueOf(child_sc_id), mCount,"","");
            }

            @Override
            public void onSelectedChanged(String gender, String classType) {

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
    public void getCenterBannerData(Banner model) {

    }

    @Override
    public void getClassifyData(Classify model) {

    }

    @Override
    public void getStoreListData(StoreList model) {
//        homeStoreViewAdapter = new NearStoreViewAdapter(getActivity(), model.getDatas().getStore_list());
//        mPullLoadMoreRecyclerView.setAdapter(homeStoreViewAdapter);
//        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//        setOrientation2();
    }

    @Override
    public void getNearStoreListData(NearStoreList model) {
        homeStoreViewAdapter = new NearStoreViewAdapter(getActivity(), model.getDatas());
        mPullLoadMoreRecyclerView.setAdapter(homeStoreViewAdapter);
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        setOrientation2();
    }

    @Override
    public void getStoreAareaList(StoreAreaList model) {

    }

    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(getActivity(), "获取数据中...", false, null);
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
            mCount = mCount + 1;
            homeFragmentBannerPresenter.getStoreListData("", "", String.valueOf(mCount));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

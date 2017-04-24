package com.wangjia.yijiale.otherfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.activity.GoodsDetailActivity;
import com.wangjia.yijiale.adapter.CollectionViewAdapter;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.CollectionList;
import com.wangjia.yijiale.iview.MyCollectionActivityView;
import com.wangjia.yijiale.presenter.MyCollectionActivityPresenter;
import com.wangjia.yijiale.presenter.impl.MyCollectionActivityPresenterImpl;
import com.wangjia.yijiale.recycle.PullLoadMoreRecyclerView;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的收藏
 */
public class CollectionGoodsFragment extends Fragment implements MyCollectionActivityView {
    @Bind(R.id.recycler_view)
    PullLoadMoreRecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private MyCollectionActivityPresenter myCollectionActivityPresenter;
    CollectionViewAdapter adapter;
    List<String> datas;
    private int mCount = 1;
    private View pview;
    private Intent tIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (pview == null) {//优化View减少View的创建次数
            //该部分可通过xml文件设计Fragment界面，再通过LayoutInflater转换为View组件
            //这里通过代码为fragment添加一个TextView
            View view = inflater.inflate(R.layout.fragment_collection, container, false);
            tIntent = new Intent();
            ButterKnife.bind(this, view);
            initData();
            initView();
            pview = view;
        }
        ViewGroup parent = (ViewGroup) pview.getParent();
        if (parent != null) {//如果View已经添加到容器中，要进行删除，负责会报错
            parent.removeView(pview);
        }
        ButterKnife.bind(this, pview);
        return pview;
    }

    public void initData() {
        //显示下拉刷新
        recyclerView.setRefreshing(true);
        //设置分割线
        //mPullLoadMoreRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setGridLayout(1);
        myCollectionActivityPresenter = new MyCollectionActivityPresenterImpl(this, getActivity());
        adapter = new CollectionViewAdapter(getActivity(), null);
        recyclerView.setAdapter(adapter);
        myCollectionActivityPresenter.getCollectionList(YiApplication.getInstance().getToken(), mCount+"");
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
    }

    public void initView() {
        adapter.setmOnItemClickListener(new CollectionViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int postion, CollectionList.DatasBean.FavoritesListBean bean) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.putExtra("goods_id", bean.getGoods_id());
                intent.putExtra("store_id", String.valueOf(bean.getStore_id()));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }

            @Override
            public void onDeleteBtnCilck(View v, int layoutPostion, CollectionList.DatasBean.FavoritesListBean bean) {
                myCollectionActivityPresenter.setGoodsCollect(bean.getGoods_id());
            }
        });
    }


    @Override
    public void getData(CollectionList model) {
        recyclerView.setPullLoadMoreCompleted();
        if (model.getCode() == 200) {
            if (model.getDatas() != null) {
                adapter.setDatas(model);
            }
        }
    }

    @Override
    public void collectGoodsResult(BaseBean getInfo) {
        if(getInfo.getCode()== Constants.RESPONSE_SUCCESS){
            mCount =1;
            myCollectionActivityPresenter.getCollectionList(YiApplication.getInstance().getToken(), mCount+"");
        }
    }

    @Override
    public void showProgressDialog() {
        //CustomProgress.showProgress(getActivity(), "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        //CustomProgress.dissmiss();
        recyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void showError(String error) {
        //CustomProgress.dissmiss();
        recyclerView.setPullLoadMoreCompleted();
        L.e("error:" + error);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
//            setRefresh();
            mCount = 1;
            myCollectionActivityPresenter.getCollectionList(YiApplication.getInstance().getToken(), mCount+"");
        }

        @Override
        public void onLoadMore() {
            mCount = mCount + 1;
            myCollectionActivityPresenter.getCollectionList(YiApplication.getInstance().getToken(), mCount+"");
        }
    }

//    private void setRefresh() {
//        mCount = 1;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

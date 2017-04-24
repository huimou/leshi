package com.wangjia.yijiale.otherfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.adapter.CommentListViewAdapter;
import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.CommentListBean;
import com.wangjia.yijiale.entity.StoreGoodsList;
import com.wangjia.yijiale.iview.ProductListFragmentView;
import com.wangjia.yijiale.presenter.impl.ProductListFragmentPresenterImpl;
import com.wangjia.yijiale.views.DividerItemDecoration;
import com.wangjia.yijiale.views.FullyLinearLayoutManager;
import com.wangjia.yijiale.views.NoScrollRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 大作战
 */
public class CommentListFragment extends Fragment implements ProductListFragmentView {

    @Bind(R.id.recycler_view)
    NoScrollRecycleView recyclerView;
    private int mCount = 1;
    private CommentListViewAdapter
            recordViewAdapter;
    private List<String> dataList;
    private Intent tIntent;
    private int store_id;
    private int page_size =50;
    private ProductListFragmentPresenterImpl productListFragmentPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    public void initView() {
        setOrientation1();
    }

    public void initData() {
        Bundle arguments = getArguments();
        store_id = arguments.getInt("store_id");
        productListFragmentPresenter = new ProductListFragmentPresenterImpl(this, getActivity());
        recordViewAdapter = new CommentListViewAdapter(getActivity(), null);
        recyclerView.setAdapter(recordViewAdapter);
        productListFragmentPresenter.getStoreComment(store_id,1, page_size);
    }

    /**
     * 设置RecycleView布局方式
     */
    public void setOrientation1() {
        //设置布局管理
        LinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recordViewAdapter.setmOnItemClickListener(new CommentListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getData(StoreGoodsList model) {

    }

    @Override
    public void changeNum(ChangeNum model) {

    }

    @Override
    public void getStoreCommenrt(CommentListBean getInfo) {
        dataList = new ArrayList<String>();
        recordViewAdapter = new CommentListViewAdapter(getActivity(), getInfo.getDatas().getEval_list());
        recyclerView.setAdapter(recordViewAdapter);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }
}

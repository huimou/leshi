package com.wangjia.yijiale.otherfragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.adapter.PayRecordViewAdapter;
import com.wangjia.yijiale.recycle.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 大作战
 */
public class NoPayFragment extends Fragment {

    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int mCount = 1;
    private PayRecordViewAdapter
            recordViewAdapter;
    private List<String> dataList;
    private Intent tIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.all_record, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        //mPullLoadMoreRecyclerView.setRefreshing(true);
    }

    public void initView() {
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置分割线
        //mPullLoadMoreRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mPullLoadMoreRecyclerView.setGridLayout(1);
    }

    public void initData() {
        dataList = new ArrayList<String>();
        recordViewAdapter = new PayRecordViewAdapter(getActivity(), null);
        mPullLoadMoreRecyclerView.setAdapter(recordViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
    }



    private List<String> setList() {
        List<String> dataList = new ArrayList<>();
        int start = 20 * (mCount - 1);
        for (int i = start; i < 20 * mCount; i++) {
            dataList.add("Second" + i);
        }
        return dataList;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            setRefresh();
        }

        @Override
        public void onLoadMore() {
            mCount = mCount + 1;
        }
    }

    private void setRefresh() {
        mCount = 1;
    }


    /**
     * 删除选择
     */
    private  void showLogoutDialog(){
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_showmsg);
        TextView tv_message = (TextView) window.findViewById(R.id.message);
        tv_message.setText("你是否删除此订单?");
        Button cancel = (Button) window.findViewById(R.id.btn_left);
        Button ok = (Button) window.findViewById(R.id.btn_right);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注销登录操作

                dialog.dismiss();
            }
        });
    }

}

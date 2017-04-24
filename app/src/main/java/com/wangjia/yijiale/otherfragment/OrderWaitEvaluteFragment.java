package com.wangjia.yijiale.otherfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.adapter.OrderAdapter;
import com.wangjia.yijiale.entity.MyOrder;
import com.wangjia.yijiale.event.StatusBarEvent;
import com.wangjia.yijiale.iview.MyOrderActivityView;
import com.wangjia.yijiale.presenter.MyOrderActivityPresenter;
import com.wangjia.yijiale.presenter.impl.MyOrderActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.views.CustomProgress;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 待收货
 */
public class OrderWaitEvaluteFragment extends Fragment implements MyOrderActivityView {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private MyOrderActivityPresenter myOrderActivityPresenter;
    OrderAdapter adapter;
    List<String> datas;
    private View pview;
    private Intent tIntent;
    private Subscription rxSubscription;

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
            View view = inflater.inflate(R.layout.fragment_order_all, container, false);
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
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        myOrderActivityPresenter = new MyOrderActivityPresenterImpl(this, getActivity());
        adapter = new OrderAdapter(null, getContext());
        recyclerView.setAdapter(adapter);

        adapter.setmOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onCanlePayClick(View view, int postion, MyOrder.DatasBean bean) {

            }

            @Override
            public void onPayClick(View view, int postion, MyOrder.DatasBean bean) {
                //确认收货
                myOrderActivityPresenter.orderOperte(YiApplication.getInstance().getToken(), bean.getOrder_id() + "", "order_receive");
            }

            @Override
            public void onCommentClick(View view, int postion, MyOrder.DatasBean bean) {

            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
        rxSubscription = RxBus.getDefault().toObservable(StatusBarEvent.class)
                .subscribe(new Action1<StatusBarEvent>() {
                    @Override
                    public void call(StatusBarEvent statusBarEvent) {
                        if (statusBarEvent.getName().equals("Update_data")) {
                            //更新数据
                            myOrderActivityPresenter.getData(YiApplication.getInstance().getToken(),30, 1);
                        }
                    }
                });
    }

    public void initView() {
        myOrderActivityPresenter.getData(YiApplication.getInstance().getToken(),30, 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        rxSubscription.unsubscribe();
    }


    @Override
    public void getData(MyOrder model) {
        if (model.getCode()== Constants.RESPONSE_SUCCESS) {
            if (model.getDatas() != null) {
                adapter.setData(model);
            }
        }
    }

    @Override
    public void orderOperte(MyOrder getInfo) {
        if(getInfo.getCode()==Constants.RESPONSE_SUCCESS) {
            RxBus.getDefault().send(new StatusBarEvent("", "Update_data", 1));
        }else{
            ToastUtils.showToast(getActivity(),getInfo.getMsg());
        }
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
        L.e("error:" + error);
    }
}
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

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.activity.GoodsDetailActivity;
import com.wangjia.yijiale.adapter.ProductListViewAdapter;
import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.CommentListBean;
import com.wangjia.yijiale.entity.StoreGoodsList;
import com.wangjia.yijiale.event.StatusBarEvent;
import com.wangjia.yijiale.iview.ProductListFragmentView;
import com.wangjia.yijiale.presenter.ProductListFragmentPresenter;
import com.wangjia.yijiale.presenter.impl.ProductListFragmentPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.views.DividerItemDecoration;
import com.wangjia.yijiale.views.NoScrollRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 商品列表
 */
public class ProductListFragment extends Fragment implements ProductListFragmentView {
    @Bind(R.id.recycler_view)
    NoScrollRecycleView recyclerView;
    private int mCount = 1;
    private ProductListViewAdapter
            recordViewAdapter;
    private List<String> dataList;
    private Intent tIntent;
    private ProductListFragmentPresenter productListFragmentPresenter;
    public Subscription rxSubscription;
    private StoreGoodsList storeGoodsList;
    private boolean flag = true;
    private int position = -1;
    private String page = "1";
    private int store_id;

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
        storeGoodsList = new StoreGoodsList();
        rxSubscription = RxBus.getDefault().toObservable(StatusBarEvent.class)
                .subscribe(new Action1<StatusBarEvent>() {
                    @Override
                    public void call(StatusBarEvent statusBarEvent) {
                        if (statusBarEvent.getName().equals("ProductFragmentList")) {
                            if (statusBarEvent.getId().equals("add")) {
                                if(!StringFunction.isNotNull(YiApplication.getInstance().getToken())){
                                    ToastUtils.showToast(getActivity(),"亲，你尚未登录，请先登录再操作！");
                                    return;
                                }
                                flag = true;
                                position = statusBarEvent.getI();
                                productListFragmentPresenter.changeNum(storeGoodsList.getDatas().getGoods_list().get(position).getGoods_id(),
                                        String.valueOf(storeGoodsList.getDatas().getGoods_list().get(position).getCart_num() + 1),
                                        YiApplication.getInstance().getToken());
                            } else {
                                if(!StringFunction.isNotNull(YiApplication.getInstance().getToken())){
                                    ToastUtils.showToast(getActivity(),"亲，你尚未登录，请先登录再操作！");
                                    return;
                                }
                                position = statusBarEvent.getI();
                                flag = false;
                                productListFragmentPresenter.changeNum(storeGoodsList.getDatas().getGoods_list().get(position).getGoods_id(),
                                        String.valueOf(storeGoodsList.getDatas().getGoods_list().get(position).getCart_num() - 1),
                                        YiApplication.getInstance().getToken());
                            }
                        }else if(statusBarEvent.getName().equals("UpdateProductFragmentList")){
                            productListFragmentPresenter.getData(String.valueOf(store_id), page);
                        }
                    }
                });
        dataList = new ArrayList<String>();
        productListFragmentPresenter = new ProductListFragmentPresenterImpl(this, getActivity());
        recordViewAdapter = new ProductListViewAdapter(getActivity(), null);
        recyclerView.setAdapter(recordViewAdapter);
        productListFragmentPresenter.getData(String.valueOf(store_id), page);
    }

    /**
     * 设置RecycleView布局方式
     */
    public void setOrientation1() {
        //设置布局管理
//        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recordViewAdapter.setmOnItemClickListener(new ProductListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.putExtra("goods_id",storeGoodsList.getDatas().getGoods_list().get(postion).getGoods_id());
                intent.putExtra("store_id",String.valueOf(store_id));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rxSubscription.unsubscribe();
        ButterKnife.unbind(this);
    }

    /**
     * 删除选择
     */
    private void showLogoutDialog() {
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

    @Override
    public void getData(StoreGoodsList model) {
        storeGoodsList = model;
        if (model.getCode() == 200) {
            recordViewAdapter.setDatas(model, position);
        }
    }

    @Override
    public void changeNum(ChangeNum model) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            if (flag) {
                //增加
                storeGoodsList.getDatas().getGoods_list().get(position).setCart_num(storeGoodsList.getDatas().getGoods_list().get(position).getCart_num() + 1);
                recordViewAdapter.setDatas(storeGoodsList, position);
                RxBus.getDefault().send(new StatusBarEvent("dall", "UpdateProductFragmentCart", 0));
            } else {
                //减少
                storeGoodsList.getDatas().getGoods_list().get(position).setCart_num(storeGoodsList.getDatas().getGoods_list().get(position).getCart_num() - 1);
                recordViewAdapter.setDatas(storeGoodsList, position);
                RxBus.getDefault().send(new StatusBarEvent("del", "UpdateProductFragmentCart", 0));
            }
        }else if(model.getCode() == Constants.TOKEN_RESPONSE_FAILED){
            ToastUtils.showToast(getActivity(),"你尚未登录，请先登录！");
        }
//        L.TShort(getActivity(), model.getDatas());
    }

    @Override
    public void getStoreCommenrt(CommentListBean getInfo) {

    }

    @Override
    public void showProgressDialog() {
        //CustomProgress.showProgress(getActivity(), "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        //CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        //CustomProgress.dissmiss();
    }
}

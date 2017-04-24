package com.wangjia.yijiale.address;


/**
 * 设置
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.adapter.AddressListViewAdapter;
import com.wangjia.yijiale.entity.AddressManageList;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.iview.AddressManageActivityView;
import com.wangjia.yijiale.presenter.AddressManageActivityPresenter;
import com.wangjia.yijiale.presenter.impl.AddressManageActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.SPUtils;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.views.CustomProgress;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressManageActivity extends AppCompatActivity implements AddressManageActivityView {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private AddressManageActivityPresenter addressManageActivityPresenter;
    AddressListViewAdapter adapter;
    List<String> datas;
    private int mCount = 1;
    private View pview;
    private Intent tIntent;
    private int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manage);
        tIntent = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        addressManageActivityPresenter = new AddressManageActivityPresenterImpl(this, AddressManageActivity.this);
        adapter = new AddressListViewAdapter(AddressManageActivity.this, null);
        recyclerView.setAdapter(adapter);
        L.i(String.valueOf(SPUtils.get(AddressManageActivity.this, Constants.TOKEN, "")));
        addressManageActivityPresenter.getData(YiApplication.getInstance().getToken());
        //addressManageActivityPresenter.getCollectionList(String.valueOf(SPUtils.get(getActivity(), Constants.TOKEN, "")), "1");
    }

    public void initView() {
        setOrientation();
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("地址管理")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        ///addressManageActivityPresenter.getData("123");
    }

    /**
     * 设置RecycleView布局方式
     */
    public void setOrientation() {
        //设置布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddressManageActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setmOnItemClickListener(new AddressListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }

            @Override
            public void onDeleteBtnCilck(View view, int postion, AddressManageList.DatasBean.AddressListBean bean) {
                addressManageActivityPresenter.getDeleteAddress(YiApplication.getInstance().getToken(),bean.getAddress_id());
            }

            @Override
            public void onUpdateBtnCilck(View view, int postion, AddressManageList.DatasBean.AddressListBean bean) {
                Intent intent = new Intent();
                intent.setClass(AddressManageActivity.this, AddBuyAddressActivity.class);
                intent.putExtra("address_info", bean);
                // 请求码的值随便设置，但必须>=0
                requestCode = 0;
                startActivityForResult(intent, requestCode);
            }


        });
    }



    @Override
    public void getData(AddressManageList model) {
        if (model.getCode() == 200) {
            if (model.getDatas() != null) {
                adapter.setDatas(model);
            }
        }
    }

    @Override
    public void getDeleteAddress(BaseBean model) {
        addressManageActivityPresenter.getData(YiApplication.getInstance().getToken());
    }

//    @Override
//    public void getUpdateAddress(BaseBean model) {
//        addressManageActivityPresenter.getData(YiApplication.getInstance().getToken());
//    }

    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(AddressManageActivity.this, "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        CustomProgress.dissmiss();
        L.e("error:" + error);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.add_address)
    public void onClick() {
        tIntent.setClass(AddressManageActivity.this, AddBuyAddressActivity.class);
        // 请求码的值随便设置，但必须>=0
        requestCode = 0;
        startActivityForResult(tIntent, requestCode);
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据上面发送过去的请求吗来区别
        switch (resultCode) {
            case 0:
                addressManageActivityPresenter.getData(YiApplication.getInstance().getToken());
                break;
            default:
                break;
        }
    }
}

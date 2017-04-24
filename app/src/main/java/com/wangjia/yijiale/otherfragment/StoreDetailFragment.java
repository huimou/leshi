package com.wangjia.yijiale.otherfragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.MyStoreDetail;
import com.wangjia.yijiale.iview.StoreContractDetailFragmentView;
import com.wangjia.yijiale.presenter.StoreContractDetailFragmentPresenter;
import com.wangjia.yijiale.presenter.impl.StoreContractDetailFragmentPresenterImpl;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.views.CustomProgress;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/8 0008.
 * 商家详情
 */

public class StoreDetailFragment extends Fragment implements StoreContractDetailFragmentView {
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.phone_rl)
    RelativeLayout phoneRl;
    @Bind(R.id.address_rl)
    RelativeLayout addressRl;
    private StoreContractDetailFragmentPresenter storeContractDetailFragmentPresenter;
    private int store_id;
    private AlertView mAlertView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_detail, container, false);
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
        storeContractDetailFragmentPresenter.getData("2");
    }

    public void initData() {
        Bundle arguments = getArguments();
        store_id = arguments.getInt("store_id");
        storeContractDetailFragmentPresenter = new StoreContractDetailFragmentPresenterImpl(this, getActivity());
        storeContractDetailFragmentPresenter.getData(String.valueOf(store_id));
    }

    @Override
    public void getData(MyStoreDetail model) {
        if (model.getCode() == 200) {
            if(StringFunction.isNotNull(model.getDatas().getStore_phone())) {
                phone.setText(model.getDatas().getStore_phone());
            }
            if(StringFunction.isNotNull(model.getDatas().getAddr())) {
                address.setText(String.valueOf(model.getDatas().getAddr()));
            }
            if(StringFunction.isNotNull(model.getDatas().getStore_zy())) {
                content.setText(String.valueOf(model.getDatas().getStore_zy()));
            }
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
        CustomProgress.dissmiss();
        L.e("error:" + error);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.r_phone, R.id.r_address, R.id.r_info, R.id.phone_rl, R.id.address_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phone_rl:
                if (StringFunction.isNotNull(phone.getText().toString())) {
                    if (mAlertView == null) {
                        mAlertView = new AlertView(null, null, "取消", null,
                                new String[]{phone.getText().toString()},
                                getActivity(), AlertView.Style.ActionSheet, new OnItemClickListener() {
                            @Override
                            public void onItemClick(Object v, int position) {
                                if (position == 0) {
                                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone.getText().toString()));//跳转到拨号界面，同时传递电话号码
                                    startActivity(dialIntent);
                                } else {//取消
                                    mAlertView.dismiss();
                                }

                            }
                        });
                    }
                    mAlertView.show();
                }
                break;
            case R.id.address_rl:
                break;
            case R.id.r_info:
                break;
        }
    }
}

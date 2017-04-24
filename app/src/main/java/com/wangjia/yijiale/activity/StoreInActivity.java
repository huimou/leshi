package com.wangjia.yijiale.activity;


/**
 * 商家入驻
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.address.AddAddressChooseActivity;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.iview.StoreInActivityView;
import com.wangjia.yijiale.presenter.StoreInActivityPresenter;
import com.wangjia.yijiale.presenter.impl.StoreInActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreInActivity extends AppCompatActivity implements StoreInActivityView {
    @Bind(R.id.bnLogin)
    Button bnLogin;
    @Bind(R.id.store_name)
    EditText storeName;
    @Bind(R.id.detail_address_editText)
    EditText detailAddressEditText;
    @Bind(R.id.contacts_name_et)
    EditText contactsNameEt;
    @Bind(R.id.contacts_phone_et)
    EditText contactsPhoneEt;
    @Bind(R.id.tv_loc_area)
    TextView tvLocArea;
    @Bind(R.id.rl_loc_area)
    RelativeLayout rlLocArea;
    private Intent i;
    private StoreInActivityPresenter storeInActivityPresenter;
    private String shengId;
    private String shiId;
    private String quId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storein);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        storeInActivityPresenter = new StoreInActivityPresenterImpl(this, this);


    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("商家入驻")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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

    @Override
    public void store_id(BaseBean getInfo) {
        if (getInfo.getCode() == Constants.RESPONSE_SUCCESS) {
            ToastUtils.showToast(StoreInActivity.this, getInfo.getMsg());
        }
    }

    @OnClick({R.id.bnLogin, R.id.rl_loc_area})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnLogin:
                if (!StringFunction.isNotNull(storeName.getText().toString().trim())) {
                    ToastUtils.showToast(StoreInActivity.this, "亲，你尚未填写商家全称！");
                    return;
                }

                if (!StringFunction.isNotNull(tvLocArea.getText().toString().trim())) {
                    ToastUtils.showToast(StoreInActivity.this, "亲，你尚未选择地址！");
                    return;
                }

                if (!StringFunction.isNotNull(detailAddressEditText.getText().toString().trim())) {
                    ToastUtils.showToast(StoreInActivity.this, "亲，你尚未填写详细地址！");
                    return;
                }

                if (!StringFunction.isNotNull(contactsNameEt.getText().toString().trim())) {
                    ToastUtils.showToast(StoreInActivity.this, "亲，你尚未填写联系人名字！");
                    return;
                }

                if (!StringFunction.isNotNull(contactsPhoneEt.getText().toString().trim())) {
                    ToastUtils.showToast(StoreInActivity.this, "亲，你尚未填写联系人手机号！");
                    return;
                }
                storeInActivityPresenter.getData(YiApplication.getInstance().getToken(), storeName.getText().toString(),
                        tvLocArea.getText().toString(), detailAddressEditText.getText().toString(),
                        contactsNameEt.getText().toString(), contactsPhoneEt.getText().toString(), "电子邮箱",
                        contactsNameEt.getText().toString(), "身份证号", "手持身份证照片");
                //提交
                break;
            case R.id.rl_loc_area:
                Intent choiceAddress = new Intent(StoreInActivity.this, AddAddressChooseActivity.class);
                startActivityForResult(choiceAddress, 200);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 200:
                if (resultCode == RESULT_OK) {
                    L.e(data.getStringExtra("address").toString());
                    tvLocArea.setText(data.getStringExtra("address").toString());
                    shengId = data.getStringExtra("shengID").toString();
                    shiId = data.getStringExtra("shiID").toString();
                    L.e(shengId);
                    L.e(data.getStringExtra("shiID").toString());
                    if (data.hasExtra("shiID")) {
                        quId = data.getStringExtra("quID").toString();
                        L.e(data.getStringExtra("quID").toString());
                    }
                }
                break;
        }
    }
}

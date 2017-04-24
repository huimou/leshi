package com.wangjia.yijiale.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.entity.AddAddress;
import com.wangjia.yijiale.entity.AddressManageList;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.iview.AddAddressActivityView;
import com.wangjia.yijiale.presenter.AddAddressActivityPresenter;
import com.wangjia.yijiale.presenter.impl.AddAddressActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.views.CustomProgress;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 添加购物地址（编辑）
 */
public class AddBuyAddressActivity extends AppCompatActivity implements AddAddressActivityView {
    @Bind(R.id.et_receiving_p_name)
    EditText etReceivingPName;
    @Bind(R.id.et_contact_phone)
    EditText etContactPhone;
    @Bind(R.id.rl_loc_area)
    RelativeLayout rlLocArea;
    @Bind(R.id.tv_loc_area)
    TextView tvLocArea;
    @Bind(R.id.et_detail_address)
    EditText etDetailAddress;
    @Bind(R.id.cb_set_normal_address)
    CheckBox checkBox;
    String shengId = "", shiId = "", quId = "";
    Intent fIntent;
    String addsId = "";//编辑地址 具有地址ID
    @Bind(R.id.et_contact_yz)
    EditText etContactYz;
    @Bind(R.id.deflut_address_cb)
    CheckBox deflutAddressCb;
    private AddAddressActivityPresenter addAddressActivityPresenter;
    private int resultCode = 0;
    private String is_check = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.add_buy_address);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    public void initData() {
        addAddressActivityPresenter = new AddAddressActivityPresenterImpl(this, AddBuyAddressActivity.this);
    }

    public void initView() {
        fIntent = getIntent();
        String title = "添加新地址";
        if (fIntent.hasExtra("address_info")) {//编辑
            title = "编辑地址";
            AddressManageList.DatasBean.AddressListBean bean =
                    (AddressManageList.DatasBean.AddressListBean) fIntent.getSerializableExtra("address_info");
            etReceivingPName.setText(bean.getTrue_name());
            etReceivingPName.setSelection(bean.getTrue_name().length());
            etContactPhone.setText(bean.getTel_phone());
            tvLocArea.setText(bean.getProvince_name() + bean.getCity_name() + bean.getArea_name());
            etDetailAddress.setText(bean.getAddress());
            etContactYz.setText(bean.getPost_code());
            if(bean.getIs_default().equals("0")){
                deflutAddressCb.setChecked(false);
            }else if(bean.getIs_default().equals("1")){
                deflutAddressCb.setChecked(true);
            }
            shengId = bean.getProvince_id();
            shiId = bean.getCity_id();
            quId = bean.getArea_id();
            addsId = bean.getAddress_id();
        }
        new Titlebulder(this).setTitleName(title)
                .setLeftImage(R.mipmap.arrowleft)
                .setLeftOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .setRightText(R.string.save)
                .setRightTvOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String consignee_name = etReceivingPName.getText().toString();
                        String address = etDetailAddress.getText().toString();
                        String mobile = etContactPhone.getText().toString();
                        if (consignee_name.length() > 0 && address.length() > 0 && mobile.length() > 0) {
                            if (etDetailAddress.getText().length() <= 0) {
                                L.TShort(getBaseContext(), "请填写详细地址");
                                return;
                            }

                            if(deflutAddressCb.isChecked()){
                                is_check = "1";
                            }else{
                                is_check = "0";
                            }

                            if (!addsId.equals("")) {//更新地址
                                CustomProgress.showProgress(AddBuyAddressActivity.this, "修改中..", false, null);
                                addAddressActivityPresenter.getUpdateAddress(YiApplication.getInstance().getToken(), addsId, consignee_name, shiId, quId, mobile, is_check, etContactYz.getText().toString().trim(), etDetailAddress.getText().toString().trim(), shengId);
                            } else {
//                                CustomProgress.showProgress(AddBuyAddressActivity.this, "加载中..", false, null);
                                addAddressActivityPresenter.getData(YiApplication.getInstance().getToken(), consignee_name, shiId, quId, mobile, is_check, etContactYz.getText().toString().trim(), etDetailAddress.getText().toString().trim(), shengId);
                            }
                        } else {
                            L.TShort(getBaseContext(), "请完整填写收货人资料");
                        }
                    }
                });
        rlLocArea.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choiceAddress = new Intent(AddBuyAddressActivity.this, AddAddressChooseActivity.class);
                startActivityForResult(choiceAddress, 200);
            }
        });
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

    /*public void setAddress(String token, String consignee_name, String provinceId, String cityId,
                           String districtId, String address, String mobile) {
        OkHttpUtils
                .post()//
                .url(URIUnifiedList.setRECEIVINGADDRESS)//
                .addParams("token", token)
                .addParams("consignee", consignee_name)//收件人
                .addParams("province", provinceId)//省市区ID
                .addParams("city", cityId)
                .addParams("district", districtId)
                .addParams("address", address)//详细地址
                .addParams("mobile", mobile)//联系电话
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        CustomProgress.dissmiss();
                    }

                    @Override
                    public void onResponse(String response) {
                        CustomProgress.dissmiss();
                        L.e("添加收货地址接口 ：" + response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            if (jo.getString("code").equals("200")) {
                                //添加成功
                                Intent i = new Intent(AddBuyAddressActivity.this, AddressManageActivity.class);
                                setResult(RESULT_OK, i);
                                finish();
                            } else {
                                L.TShort(getApplicationContext(), jo.getString("message").toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    */
     /*
    public void updateAddress(String userId, String addressId, String consignee_name, String provinceId, String cityId,
                              String districtId, String address, String mobile) {
        OkHttpUtils
                .post()//
                .url(URIUnifiedList.updateADDRESSLIST)//
                .addParams("user_id", userId)
                .addParams("address_id", addressId)
                .addParams("consignee", consignee_name)//收件人
                .addParams("province", provinceId)//省市区ID
                .addParams("city", cityId)
                .addParams("district", districtId)
                .addParams("address", address)//详细地址
                .addParams("mobile", mobile)//联系电话
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        CustomProgress.dissmiss();
                    }

                    @Override
                    public void onResponse(String response) {
                        CustomProgress.dissmiss();
                        L.e("更新收货地址接口 ：" + response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            L.TShort(getApplicationContext(),jo.getString("message").toString());
                            if(jo.getString("code").equals("200")){
                                //地址修改成功
                                Intent i = new Intent(AddBuyAddressActivity.this, AddressManageActivity.class);
                                setResult(RESULT_OK,i);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }*/


    @Override
    public void getData(AddAddress model) {
        Intent mIntent = new Intent();
        // 设置结果，并进行传送
        this.setResult(resultCode, mIntent);
        this.finish();
    }

    @Override
    public void getUpdateAddress(BaseBean model) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            Intent mIntent = new Intent();
            // 设置结果，并进行传送
            this.setResult(resultCode, mIntent);
            this.finish();
        }
    }

    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(AddBuyAddressActivity.this, "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        CustomProgress.dissmiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}

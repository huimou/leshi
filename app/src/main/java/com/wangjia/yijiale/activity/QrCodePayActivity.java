package com.wangjia.yijiale.activity;


/**
 * 二维码支付
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.QrCode;
import com.wangjia.yijiale.iview.QrCodeActivityView;
import com.wangjia.yijiale.presenter.impl.QrCodeActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QrCodePayActivity extends AppCompatActivity implements QrCodeActivityView {

    @Bind(R.id.play_name)
    TextView playName;
    @Bind(R.id.input_money_et)
    EditText inputMoneyEt;
    @Bind(R.id.submit_pay_tv)
    TextView submitPayTv;
    private Intent i;
    private QrCodeActivityPresenterImpl qrCodeActivityPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_pay);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {


    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("二维码支付")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        submitPayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringFunction.isNotNull(inputMoneyEt.getText().toString())) {
                    qrCodeActivityPresenterImpl = new QrCodeActivityPresenterImpl(QrCodePayActivity.this, QrCodePayActivity.this);
                    qrCodeActivityPresenterImpl.payMoneyForStore(YiApplication.getInstance().getToken(),
                            inputMoneyEt.getText().toString(), getIntent().getIntExtra("store_id", 0));
                } else {
                    ToastUtils.showToast(QrCodePayActivity.this, "请输入转账金额！");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void shop_code(QrCode getInfo) {
//        playName.setText(getInfo.getDatas().getStore_name() + ",向TA付款");
//        Glide.with(this).load(getInfo.getDatas().getCode_url()).into(qrCodeIv);
    }

    @Override
    public void payMoneyForStore(BaseBean baseBean) {
        if (baseBean.getCode() == Constants.RESPONSE_SUCCESS) {

        }
        ToastUtils.showToast(QrCodePayActivity.this, baseBean.getMsg());
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

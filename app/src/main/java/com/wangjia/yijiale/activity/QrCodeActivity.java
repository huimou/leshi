package com.wangjia.yijiale.activity;


/**
 * 二维码
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.QrCode;
import com.wangjia.yijiale.iview.QrCodeActivityView;
import com.wangjia.yijiale.presenter.impl.QrCodeActivityPresenterImpl;
import com.wangjia.yijiale.utils.Titlebulder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QrCodeActivity extends AppCompatActivity implements QrCodeActivityView {
    @Bind(R.id.play_name)
    TextView playName;
    @Bind(R.id.qr_code_iv)
    ImageView qrCodeIv;
    private Intent i;
    private QrCodeActivityPresenterImpl qrCodeActivityPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        qrCodeActivityPresenterImpl = new QrCodeActivityPresenterImpl(this, QrCodeActivity.this);
        qrCodeActivityPresenterImpl.getData(getIntent().getIntExtra("store_id",0));

    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("二维码")
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
    public void shop_code(QrCode getInfo) {
        playName.setText(getInfo.getDatas().getStore_name()+",向TA付款");
        Glide.with(this).load(getInfo.getDatas().getCode_url()).into(qrCodeIv);
    }

    @Override
    public void payMoneyForStore(BaseBean baseBean) {

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

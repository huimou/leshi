package com.wangjia.yijiale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.AboutBean;
import com.wangjia.yijiale.entity.CodeBean;
import com.wangjia.yijiale.entity.Register;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.entity.ZhifuShiWuBean;
import com.wangjia.yijiale.iview.RegisterActivityView;
import com.wangjia.yijiale.presenter.impl.RegisterActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.SPUtils;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.TimeUtil;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.views.CustomProgress;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityView {
    @Bind(R.id.titlepg_left_iv)
    ImageView titlepgLeftIv;
    @Bind(R.id.titlepg_left_tv)
    TextView titlepgLeftTv;
    @Bind(R.id.titlepg_between_tv)
    TextView titlepgBetweenTv;
    @Bind(R.id.titlepg_right_iv)
    ImageView titlepgRightIv;
    @Bind(R.id.titlepg_right_tv)
    TextView titlepgRightTv;
    @Bind(R.id.titlepg)
    RelativeLayout titlepg;
    @Bind(R.id.getCode)
    TextView getCode;
    @Bind(R.id.bnRegister)
    Button bnRegister;
    @Bind(R.id.user_phone_et)
    EditText userPhoneEt;
    @Bind(R.id.code_et)
    EditText codeEt;
    @Bind(R.id.in_put_password_et)
    EditText inPutPasswordEt;
    private Intent i;
    private TimeUtil timeUtil;
    private RegisterActivityPresenterImpl registerActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        registerActivityPresenter = new RegisterActivityPresenterImpl(this, RegisterActivity.this);
    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("注册")
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

    @OnClick({R.id.bnRegister, R.id.getCode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnRegister:
                if (!StringFunction.isNotNull(userPhoneEt.getText().toString())) {
                    ToastUtils.showToast(RegisterActivity.this, "亲，你尚未输入手机号,请输入手机号！");
                    return;
                }
                if (!StringFunction.isNotNull(inPutPasswordEt.getText().toString())) {
                    ToastUtils.showToast(RegisterActivity.this, "亲，你尚未输入密码,请输入密码！");
                    return;
                }
                if (!StringFunction.isNotNull(codeEt.getText().toString())) {
                    ToastUtils.showToast(RegisterActivity.this, "亲，你尚未输入验证码,请输入验证码！");
                    return;
                }
                registerActivityPresenter.getData(userPhoneEt.getText().toString(),
                        inPutPasswordEt.getText().toString(), codeEt.getText().toString());
                break;

            case R.id.getCode:

                if (!StringFunction.isNotNull(userPhoneEt.getText().toString())) {
                    ToastUtils.showToast(RegisterActivity.this, "亲，你尚未输入手机号,请输入手机号！");
                    return;
                }

                registerActivityPresenter.getCode(userPhoneEt.getText().toString(), "1");

                break;
        }
    }

    @Override
    public void getData(Register model) {

        if (model.getCode() == Constants.RESPONSE_SUCCESS && StringFunction.isNotNull(model.getDatas())) {
            //注册成功
            SPUtils.put(getApplicationContext(), Constants.MEMBER_PASSWORD, inPutPasswordEt.getText().toString());
            SPUtils.put(getApplicationContext(), Constants.TOKEN, model.getDatas().getToken());
            SPUtils.put(getApplicationContext(), Constants.MEMBER_NAME, model.getDatas().getMember_name());
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            this.startActivity(intent);
        }
        ToastUtils.showToast(RegisterActivity.this, model.getMsg());
    }

    @Override
    public void getCode(CodeBean model) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            if (timeUtil == null) {
                timeUtil = new TimeUtil(this, Constants.GET_CODE_TIME, 1000, getCode);
                timeUtil.start();
            }
        }
        ToastUtils.showToast(RegisterActivity.this, model.getMsg());
    }

    @Override
    public void getAbout(AboutBean getInfo) {

    }

    @Override
    public void showVip(ShowVipBean getInfo) {

    }

    @Override
    public void shiwuOrder(ZhifuShiWuBean getInfo) {

    }

    @Override
    public void orderSubmitPlay(SubmitOrderBean getInfo) {

    }

    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(RegisterActivity.this, "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        CustomProgress.dissmiss();
    }
}

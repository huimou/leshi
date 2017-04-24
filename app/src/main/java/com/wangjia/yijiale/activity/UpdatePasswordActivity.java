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
import com.wangjia.yijiale.entity.CodeBean;
import com.wangjia.yijiale.entity.UpdatePasswordBean;
import com.wangjia.yijiale.iview.UpdatePasswordActivityView;
import com.wangjia.yijiale.presenter.impl.UpdatePasswordActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.TimeUtil;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.views.CustomProgress;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class UpdatePasswordActivity extends AppCompatActivity implements UpdatePasswordActivityView {
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
    private UpdatePasswordActivityPresenterImpl updatePasswordActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        updatePasswordActivityPresenter = new UpdatePasswordActivityPresenterImpl(this, this);
    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("修改密码")
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
                    ToastUtils.showToast(UpdatePasswordActivity.this, "亲，你尚未输入手机号,请输入手机号！");
                    return;
                }
                if (!StringFunction.isNotNull(inPutPasswordEt.getText().toString())) {
                    ToastUtils.showToast(UpdatePasswordActivity.this, "亲，你尚未输入密码,请输入密码！");
                    return;
                }
                if (!StringFunction.isNotNull(codeEt.getText().toString())) {
                    ToastUtils.showToast(UpdatePasswordActivity.this, "亲，你尚未输入验证码,请输入验证码！");
                    return;
                }
                updatePasswordActivityPresenter.getData(userPhoneEt.getText().toString(), inPutPasswordEt.getText().toString(),codeEt.getText().toString());
                break;

            case R.id.getCode:

                if (!StringFunction.isNotNull(userPhoneEt.getText().toString())) {
                    ToastUtils.showToast(UpdatePasswordActivity.this, "亲，你尚未输入手机号,请输入手机号！");
                    return;
                }

                updatePasswordActivityPresenter.getCode(userPhoneEt.getText().toString(), "2");

                break;
        }
    }

    @Override
    public void getData(UpdatePasswordBean model) {

        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            //密码修改成功
            Intent intent = new Intent(UpdatePasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        ToastUtils.showToast(UpdatePasswordActivity.this, model.getMsg());
    }

    @Override
    public void getCode(CodeBean model) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            if (timeUtil == null) {
                timeUtil = new TimeUtil(this, Constants.GET_CODE_TIME, 1000, getCode);
                timeUtil.start();
            }
        }
        ToastUtils.showToast(UpdatePasswordActivity.this, model.getMsg());
    }

    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(UpdatePasswordActivity.this, "获取数据中...", false, null);
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

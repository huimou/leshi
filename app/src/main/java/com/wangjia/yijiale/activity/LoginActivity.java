package com.wangjia.yijiale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.Login;
import com.wangjia.yijiale.iview.LoginActivityView;
import com.wangjia.yijiale.presenter.LoginActivityPresenter;
import com.wangjia.yijiale.presenter.impl.LoginActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.SPUtils;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.views.CustomProgress;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {
    @Bind(R.id.member_mobile)
    EditText memberMobile;
    @Bind(R.id.member_passwd)
    EditText memberPasswd;
    private LoginActivityPresenter loginActivityPresenter;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        loginActivityPresenter = new LoginActivityPresenterImpl(this, LoginActivity.this);
    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("登录")
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
    public void getData(Login model) {
        try {
            if (model.getCode()==200) {
                //登录成功
                SPUtils.put(LoginActivity.this, Constants.TOKEN, model.getDatas().getToken());
                SPUtils.put(LoginActivity.this, Constants.NiChen, model.getDatas().getMember_nickname());
                SPUtils.put(LoginActivity.this, Constants.Sex, model.getDatas().getMember_sex());
                SPUtils.put(LoginActivity.this, Constants.Age, model.getDatas().getMember_age());
                SPUtils.put(LoginActivity.this, Constants.RealName, model.getDatas().getMember_truename());
                SPUtils.put(LoginActivity.this, Constants.CardNum, model.getDatas().getMember_idcard());
                SPUtils.put(LoginActivity.this, Constants.MEMBER_MOBILE, model.getDatas().getMember_name());
//                SPUtils.put(LoginActivity.this, Constants.MEMBER_NAME, model.getDatas().getMember_name());
                SPUtils.put(LoginActivity.this, Constants.MEMBER_AVATAR, model.getDatas().getMember_avatar());
                i.setClass(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
            L.TShort(LoginActivity.this, model.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProgressDialog() {
        if(!this.isFinishing()) {
            CustomProgress.showProgress(LoginActivity.this, "获取数据中...", false, null);
        }
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        CustomProgress.dissmiss();
    }

    @OnClick({R.id.bnLogin, R.id.wxLogin, R.id.regist_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnLogin:
                if (!StringFunction.isNotNull(memberMobile.getText().toString())) {
                    ToastUtils.showToast(LoginActivity.this, "亲，你尚未输入手机号,请输入手机号！");
                    return;
                }
                if (!StringFunction.isNotNull(memberPasswd.getText().toString())) {
                    ToastUtils.showToast(LoginActivity.this, "亲，你尚未输入密码,请输入密码！");
                    return;
                }
                loginActivityPresenter.getData(memberMobile.getText().toString(), memberPasswd.getText().toString());
                break;
            case R.id.wxLogin:

                break;
            case R.id.regist_user:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                this.startActivity(intent);
                break;
        }
    }
}

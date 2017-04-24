package com.wangjia.yijiale.activity;


/**
 * 设置
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.SPUtils;
import com.wangjia.yijiale.utils.Titlebulder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySettingActivity extends Activity {
    @Bind(R.id.my_mobile_tv)
    TextView myMobileTv;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        myMobileTv.setText((String) SPUtils.get(this, Constants.MEMBER_MOBILE, ""));
    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("设置")
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


    @OnClick({R.id.exit, R.id.r_safe})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exit:
                showLogoutDialog();
                break;
            case R.id.r_safe:
                i = new Intent(MySettingActivity.this, UpdatePasswordActivity.class);
                startActivity(i);
                break;
        }
    }


    /**
     * 注销登录
     */
    private void showLogoutDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_showmsg);
        TextView tv_message = (TextView) window.findViewById(R.id.message);
        tv_message.setText("你是否要退出登录?");
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
                i = new Intent(MySettingActivity.this, LoginActivity.class);
                startActivity(i);
                SPUtils.clear(MySettingActivity.this);
                YiApplication.getInstance().exitActivity();
                finish();
                dialog.dismiss();
            }
        });
    }
}

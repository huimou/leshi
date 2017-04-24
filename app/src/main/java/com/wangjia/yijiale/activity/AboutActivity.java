package com.wangjia.yijiale.activity;


/**
 * 设置
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.AboutBean;
import com.wangjia.yijiale.entity.CodeBean;
import com.wangjia.yijiale.entity.Register;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.entity.ZhifuShiWuBean;
import com.wangjia.yijiale.iview.RegisterActivityView;
import com.wangjia.yijiale.presenter.impl.RegisterActivityPresenterImpl;
import com.wangjia.yijiale.utils.Titlebulder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements RegisterActivityView {
    @Bind(R.id.me_webV)
    WebView meWebV;
    private Intent i;
    private RegisterActivityPresenterImpl registerActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        registerActivityPresenter = new RegisterActivityPresenterImpl(this, AboutActivity.this);
        registerActivityPresenter.getAbout();

    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("关于我们")
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
    public void getData(Register model) {

    }

    @Override
    public void getCode(CodeBean model) {

    }

    @Override
    public void getAbout(AboutBean getInfo) {
        meWebV.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
//        meWebV.loadData(getInfo.getDatas(), "text/html", "utf-8");
        meWebV.loadData(getInfo.getDatas(), "text/html; charset=UTF-8", null);//这种写法可以正确解码
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

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }
}

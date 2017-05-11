package com.wangjia.yijiale.activity;


/**
 * 提现
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.VipSubmitBean;
import com.wangjia.yijiale.iview.TxActivityView;
import com.wangjia.yijiale.presenter.TxActivityPresenter;
import com.wangjia.yijiale.presenter.impl.TxActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TxActivity extends AppCompatActivity implements TxActivityView {
    @Bind(R.id.bank_code_et)
    EditText bankCodeEt;
    @Bind(R.id.bank_name_ed)
    EditText bankNameEd;
    @Bind(R.id.bank_company_name_et)
    EditText bankCompanyNameEt;
    @Bind(R.id.zhihang_name_et)
    EditText zhihangNameEt;
    @Bind(R.id.bnLogin)
    Button bnLogin;
    @Bind(R.id.tixian_jine_et)
    EditText tixianJineEt;
    @Bind(R.id.shengyu_jine_tv)
    TextView shengyuJineTv;
    private Intent i;
    private TxActivityPresenter txActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx);
        i = new Intent();
        ButterKnife.bind(this);
        initView();
        initData();
    }

    public void initData() {
        txActivityPresenter = new TxActivityPresenterImpl(this, this);
        txActivityPresenter.showVip();
    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("提现")
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
    public void showVip(ShowVipBean getInfo) {
        if (StringFunction.isNotNull(getInfo.getDatas()) && StringFunction.isNotNull(getInfo.getDatas().getMember_info()) &&
                StringFunction.isNotNull(getInfo.getDatas().getMember_info().getAvailable_predeposit())) {
            shengyuJineTv.setText("￥:" + getInfo.getDatas().getMember_info().getAvailable_predeposit());
        }
    }

    @Override
    public void vipTxApply(VipSubmitBean bean) {
        if (bean.getCode() == Constants.RESPONSE_SUCCESS) {
            finish();
        }
        ToastUtils.showToast(TxActivity.this, bean.getMsg());
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

    @OnClick({R.id.bnLogin})
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.bnLogin:
                    if (!StringFunction.isNotNull(YiApplication.getInstance().getToken())) {
                        ToastUtils.showToast(TxActivity.this, "亲，你尚未登录，请先登录！");
                        return;
                    }

                    String replace = shengyuJineTv.getText().toString().replace("￥:", "");
                    if (Double.parseDouble(replace) == 0.00d) {
                        ToastUtils.showToast(TxActivity.this, "亲，你账户余额为0不能提现！");
                        return;
                    }

                    if (!StringFunction.isNotNull(tixianJineEt.getText().toString().toString())) {
                        ToastUtils.showToast(TxActivity.this, "亲，请输入提现金额！");
                        return;
                    }

                    if (Double.parseDouble(tixianJineEt.getText().toString().toString()) > Double.parseDouble(replace)) {
                        ToastUtils.showToast(TxActivity.this, "亲，提现金额不能大于你剩余金额！");
                        return;
                    }

                    if (!StringFunction.isNotNull(bankCodeEt.getText().toString().toString())) {
                        ToastUtils.showToast(TxActivity.this, "亲，请输入银行账号！");
                        return;
                    }

                    if (!StringFunction.isNotNull(bankNameEd.getText().toString().toString())) {
                        ToastUtils.showToast(TxActivity.this, "亲，请输入收款银行账户名！");
                        return;
                    }

                    if (!StringFunction.isNotNull(bankCompanyNameEt.getText().toString().toString())) {
                        ToastUtils.showToast(TxActivity.this, "亲，请输入收款银行名称！");
                        return;
                    }

                    if (!StringFunction.isNotNull(zhihangNameEt.getText().toString().toString())) {
                        ToastUtils.showToast(TxActivity.this, "亲，请输入银行开户所在城市名称！");
                        return;
                    }

                    txActivityPresenter.vipTxApply(YiApplication.getInstance().getToken(), tixianJineEt.getText().toString(),
                            bankCompanyNameEt.getText().toString(), bankCodeEt.getText().toString(), bankNameEd.getText().toString(),
                            zhihangNameEt.getText().toString().trim(), zhihangNameEt.getText().toString().trim());
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

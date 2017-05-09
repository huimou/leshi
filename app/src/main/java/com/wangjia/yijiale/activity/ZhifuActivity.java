package com.wangjia.yijiale.activity;


/**
 * 设置
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.AboutBean;
import com.wangjia.yijiale.entity.CodeBean;
import com.wangjia.yijiale.entity.PayResult;
import com.wangjia.yijiale.entity.Register;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.entity.ZhifuShiWuBean;
import com.wangjia.yijiale.iview.RegisterActivityView;
import com.wangjia.yijiale.presenter.impl.RegisterActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhifuActivity extends AppCompatActivity implements RegisterActivityView {

    @Bind(R.id.confirm_title)
    LinearLayout confirmTitle;
    @Bind(R.id.zhifubao_rl)
    RelativeLayout zhifubaoRl;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.weixin_rl)
    RelativeLayout weixinRl;
    @Bind(R.id.recommend)
    RelativeLayout recommend;
    private RegisterActivityPresenterImpl registerActivityPresenter;
    private ZhifuShiWuBean getInfo;
    private static final int SDK_PAY_FLAG = 1;
    private String order_id;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhifu);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        String pay_sn = getIntent().getStringExtra("pay_sn");
        registerActivityPresenter = new RegisterActivityPresenterImpl(this, ZhifuActivity.this);
        registerActivityPresenter.shiwuOrder(pay_sn);

    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("支付")
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


    @OnClick({R.id.zhifubao_rl, R.id.weixin_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhifubao_rl:
                //支付宝
                type = 1;
                registerActivityPresenter.orderSubmitPlay(getInfo.getDatas().getPay_info().getPay_sn(), "alipay","0");
                break;
            case R.id.weixin_rl:
                //余额支付
                type = 2;
                registerActivityPresenter.orderSubmitPlay(getInfo.getDatas().getPay_info().getPay_sn(), "alipay","1");
                break;


        }
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void alipay(final String payInfo) {

        System.out.println("启动了支付宝收银台");

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(ZhifuActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(ZhifuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ZhifuActivity.this, DetailOrderActivity.class);
                        intent.putExtra("order_id", Integer.parseInt(order_id));
                        intent.putExtra("order_name", order_id);
                        ZhifuActivity.this.startActivity(intent);
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(ZhifuActivity.this, "支付结果确认中", Toast.LENGTH_SHORT)
                                    .show();

                        } else if (TextUtils.equals(resultStatus, "4000")) {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(ZhifuActivity.this, "支付失败", Toast.LENGTH_SHORT)
                                    .show();
//                            loadUrl(rebuildUrl(WebViewUtil.alipay_back_success_url));
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            Toast.makeText(ZhifuActivity.this, "用户取消", Toast.LENGTH_SHORT)
                                    .show();
                        } else if (TextUtils.equals(resultStatus, "6002")) {
                            // Toast.makeText(context, "网络错误",
                            // Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ZhifuActivity.this, "支付失败", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    @Override
    public void getData(Register model) {

    }

    @Override
    public void getCode(CodeBean model) {

    }

    @Override
    public void getAbout(AboutBean getInfo) {

    }

    @Override
    public void showVip(ShowVipBean getInfo) {

    }

    @Override
    public void shiwuOrder(ZhifuShiWuBean getInfo) {
        //实物支付
        this.getInfo = getInfo;
    }

    @Override
    public void orderSubmitPlay(SubmitOrderBean submitOrderBean) {
        //确认订单
        if (submitOrderBean.getDatas() != null && submitOrderBean.getDatas().getAlipay_str() != null &&  type == 1) {
//            EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
            order_id = submitOrderBean.getDatas().getOrder_id();
            String s = htmlReplace(submitOrderBean.getDatas().getAlipay_str());
            alipay(s);
        }else if(submitOrderBean.getCode()== Constants.RESPONSE_SUCCESS &&  type == 2){
            ToastUtils.showToast(ZhifuActivity.this,"账户余额不足，请更换其他支付方式！");
        }else if(submitOrderBean.getCode()== 201 &&  type == 2){
            ToastUtils.showToast(ZhifuActivity.this,"支付成功！");
            Intent intent = new Intent(ZhifuActivity.this, DetailOrderActivity.class);
            intent.putExtra("order_id", Integer.parseInt(order_id));
            intent.putExtra("order_name", order_id);
            ZhifuActivity.this.startActivity(intent);
            setResult(RESULT_OK);
            finish();
        }
    }

    /**
     * (特殊字符替换)
     *
     * @return String    返回类型
     * @author xsw
     * @2016-12-4下午03:10:03
     */
    public static String htmlReplace(String str) {
//        str = str.replace("&ldquo;", "“");
//        str = str.replace("&rdquo;", "”");
//        str = str.replace("&nbsp;", " ");
        str = str.replaceAll("&amp;", "&");
//        str = str.replace("&#39;", "'");
//        str = str.replace("&rsquo;", "’");
//        str = str.replace("&mdash;", "—");
//        str = str.replace("&ndash;", "–");
        return str;
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

package com.wangjia.yijiale.otherfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.activity.CommentStoreActivity;
import com.wangjia.yijiale.adapter.OrderAdapter;
import com.wangjia.yijiale.entity.MyOrder;
import com.wangjia.yijiale.entity.PayResult;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.event.StatusBarEvent;
import com.wangjia.yijiale.iview.MyOrderActivityView;
import com.wangjia.yijiale.presenter.MyOrderActivityPresenter;
import com.wangjia.yijiale.presenter.impl.MyOrderActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.views.CustomProgress;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

import static com.wangjia.yijiale.activity.ZhifuActivity.htmlReplace;

/**
 * 订单-全部订单
 */
public class OrderAllFragment extends Fragment implements MyOrderActivityView {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private MyOrderActivityPresenter myOrderActivityPresenter;
    OrderAdapter adapter;
    List<String> datas;
    private View pview;
    private Intent tIntent;
    private Subscription rxSubscription;
    private static final int SDK_PAY_FLAG = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (pview == null) {//优化View减少View的创建次数
            //该部分可通过xml文件设计Fragment界面，再通过LayoutInflater转换为View组件
            //这里通过代码为fragment添加一个TextView
            View view = inflater.inflate(R.layout.fragment_order_all, container, false);
            tIntent = new Intent();
            ButterKnife.bind(this, view);
            initData();
            initView();
            pview = view;
        }
        ViewGroup parent = (ViewGroup) pview.getParent();
        if (parent != null) {//如果View已经添加到容器中，要进行删除，负责会报错
            parent.removeView(pview);
        }
        ButterKnife.bind(this, pview);
        return pview;
    }

    public void initData() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        myOrderActivityPresenter = new MyOrderActivityPresenterImpl(this, getActivity());
        adapter = new OrderAdapter(null, getContext());
        recyclerView.setAdapter(adapter);
//        myOrderActivityPresenter.getData(YiApplication.getInstance().getToken(),0,10);

        adapter.setmOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onCanlePayClick(View view, int postion, MyOrder.DatasBean bean) {
                //取消订单
                if (bean.getOrder_state() != 0) {
                    myOrderActivityPresenter.orderOperte(YiApplication.getInstance().getToken(), bean.getOrder_id() + "", "order_cancel");
                }
            }

            @Override
            public void onPayClick(View view, int postion, MyOrder.DatasBean bean) {
                //确认收货 ，付款
                if (bean.getOrder_state() == 1) {
                    //已取消，就是只有删除订单
                    myOrderActivityPresenter.orderOperte(YiApplication.getInstance().getToken(), bean.getOrder_id() + "", "order_delete");
                }else if(bean.getOrder_state() == 10) {
                    //待付款，有取消按钮，和 去付款按钮
                    // TODO: 2017/4/23
                    myOrderActivityPresenter.orderSubmitPlay(bean.getPay_sn(), "alipay");
//                    myOrderActivityPresenter.orderOperte(YiApplication.getInstance().getToken(), bean.getOrder_id() + "", "order_receive");
                }else if(bean.getOrder_state() == 20) {
                    //待发货，只有取消按钮
                    // TODO: 2017/4/23
                }else if(bean.getOrder_state() == 30) {
                    //待收货，只有确认收货按钮
                    // TODO: 2017/4/23
                    myOrderActivityPresenter.orderOperte(YiApplication.getInstance().getToken(), bean.getOrder_id() + "", "order_receive");
                }else if(bean.getOrder_state() == 40) {
                    //已收货，只有删除订单按钮 和  评价按钮
                    // TODO: 2017/4/23
//                    myOrderActivityPresenter.orderOperte(YiApplication.getInstance().getToken(), bean.getOrder_id() + "", "order_receive");
                    myOrderActivityPresenter.orderOperte(YiApplication.getInstance().getToken(), bean.getOrder_id() + "", "order_delete");
                }

            }

            @Override
            public void onCommentClick(View view, int postion, MyOrder.DatasBean bean) {
                //评论商家
                Intent intent = new Intent(getActivity(), CommentStoreActivity.class);
                intent.putExtra("order_id",bean.getOrder_id());
                intent.putExtra("order_name",bean.getStore_name());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });


        rxSubscription = RxBus.getDefault().toObservable(StatusBarEvent.class)
                .subscribe(new Action1<StatusBarEvent>() {
                    @Override
                    public void call(StatusBarEvent statusBarEvent) {
                        if (statusBarEvent.getName().equals("Update_data")) {
                            //更新数据
                            myOrderActivityPresenter.getData(YiApplication.getInstance().getToken(), 0, 1);
                        }
                    }
                });
    }

    public void initView() {
        myOrderActivityPresenter.getData(YiApplication.getInstance().getToken(), 0, 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        rxSubscription.unsubscribe();
    }


    @Override
    public void getData(MyOrder model) {
//        if (model.getCode()== Constants.RESPONSE_SUCCESS) {
//            if (model.getDatas() != null) {
//                List<MyOrder.DatasBean> datas = model.getDatas();
//                adapter.setData(model);
//                adapter = new OrderAdapter(model, getContext());
//                recyclerView.setAdapter(adapter);
//            }
//        }

        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            if (model.getDatas() != null) {
                adapter.setData(model);
            }
        }
    }

    @Override
    public void orderOperte(MyOrder getInfo) {
        //订单操作结果
        if (getInfo.getCode() == Constants.RESPONSE_SUCCESS) {
            RxBus.getDefault().send(new StatusBarEvent("", "Update_data", 1));
        } else {
            ToastUtils.showToast(getActivity(), getInfo.getMsg());
        }
    }

    @Override
    public void orderSubmitPlay(SubmitOrderBean submitOrderBean) {
        //确认订单
        if (submitOrderBean.getDatas() != null && submitOrderBean.getDatas().getAlipay_str() != null) {
//            EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
            String s = htmlReplace(submitOrderBean.getDatas().getAlipay_str());
//            String s= "alipay_sdk=alipay-sdk-php-20161101&app_id=2016080300159375&biz_content=%7B%22body%22%3A%22%E5%AE%9E%E7%89%A9%E8%AE%A2%E5%8D%95_140546133295366010%22%2C%22subject%22%3A+%22%E5%AE%9E%E7%89%A9%E8%AE%A2%E5%8D%95_140546133295366010%22%2C%22out_trade_no%22%3A+%22140546133295366010%22%2C%22passback_params%22%3A+%22order_type%253Dreal_order%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%22309.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fcs.j.cqxueao.cn%2Fmobileapp%2Fapi%2Fpayment%2Falipayapp%2Fnotify_url.php&sign_type=RSA2&timestamp=2017-04-21+23%3A41%3A41&version=1.0&sign=TQwXHflj3v9gea50bWaSF7yBt3X8Dq6RJWce5qmCNgs6qDuaPX39R9Br512eSNoK6TDOiinbfTX76BS9FqHPbEIOstFHMjNZLwSTbwswwW%2F%2FqxIzcXdxSD03qZd71zU27dZc46bpeWgoi%2Fhujm%2F3BTGNDxdhGgsRIOxGeLEkpvjli9j%2Fc70PaIoKaYTDGJ7uMBBLCeACFH7ZlPmtEDsf%2BPbKpTwy5VcQ%2B2VB0nQ1BPK1Q55TvGeaRUCqdq87BcZg%2F1wHdnhLj4y3Yavn90q3BV7klArqZsHx%2FtLk1uOep8E7mHIWCKT4lZ3Wj%2Bnu%2FC%2BXlnafD4qtq38Dgy2KcDXtBQ%3D%3D";
            alipay(s);
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
                PayTask alipay = new PayTask(getActivity());
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
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                        RxBus.getDefault().send(new StatusBarEvent("", "Update_data", 1));
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getActivity(), "支付结果确认中", Toast.LENGTH_SHORT)
                                    .show();

                        } else if (TextUtils.equals(resultStatus, "4000")) {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT)
                                    .show();
//                            loadUrl(rebuildUrl(WebViewUtil.alipay_back_success_url));
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            Toast.makeText(getActivity(), "用户取消", Toast.LENGTH_SHORT)
                                    .show();
                        } else if (TextUtils.equals(resultStatus, "6002")) {
                            // Toast.makeText(context, "网络错误",
                            // Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT)
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
    public void showProgressDialog() {
        CustomProgress.showProgress(getActivity(), "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        L.e("error:" + error);
    }
}
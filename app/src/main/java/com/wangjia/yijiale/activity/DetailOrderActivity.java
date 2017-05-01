package com.wangjia.yijiale.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.adapter.GoodsDetailsAdapter;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.OrderDetails;
import com.wangjia.yijiale.iview.OrderDetailsActivityView;
import com.wangjia.yijiale.presenter.DetailsOrderDetailsPresenter;
import com.wangjia.yijiale.presenter.impl.OrderActivityDetailsImpl;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.views.CustomProgress;
import com.wangjia.yijiale.views.NoScrollRecycleView;
import com.wangjia.yijiale.views.RoundAngleImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单详情
 */
public class DetailOrderActivity extends AppCompatActivity implements OrderDetailsActivityView{

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
    @Bind(R.id.confirm_title)
    LinearLayout confirmTitle;
    @Bind(R.id.riv_image)
    RoundAngleImageView rivImage;
    @Bind(R.id.tv_veget_name)
    TextView tvVegetName;
    @Bind(R.id.name)
    LinearLayout name;
    @Bind(R.id.tv_veget_time)
    TextView tvVegetTime;
    @Bind(R.id.rl_view)
    NoScrollRecycleView rlView;
    @Bind(R.id.tv_total)
    TextView tvTotal;
    @Bind(R.id.tv_total_money)
    TextView tvTotalMoney;
    @Bind(R.id.order_number)
    TextView orderNumber;
    @Bind(R.id.tv_order_time)
    TextView tvOrderTime;
    @Bind(R.id.tv_pay)
    TextView tvPay;
    @Bind(R.id.tv_beizhu)
    TextView tvBeizhu;
    private int order_id;//传回来的订单id
    DetailsOrderDetailsPresenter detailsOrderDetailsPresenter;
    GoodsDetailsAdapter adapter;
    private static final String TAG = "DetailOrderActivity";
    private String order_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        ButterKnife.bind(this);
        order_id = getIntent().getIntExtra("order_id",0);
        order_name = getIntent().getStringExtra("order_name");
        Log.d(TAG, "onCreate: "+order_id);
        new Titlebulder(this).setTitleName("订单详情")
                .setLeftImage(R.mipmap.arrowleft)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            initDatas();

    }

    /**
     * 获取数据
     */
    private void initDatas() {
        detailsOrderDetailsPresenter = new OrderActivityDetailsImpl(this,this);
        detailsOrderDetailsPresenter.getData(YiApplication.getInstance().getToken(),order_id);
    }
    @Override
    public void getData(OrderDetails model) {
        if (model!=null){
            Picasso.with(this).load(model.getDatas().getStore_avatar()).into(rivImage);
            List<OrderDetails.DatasBean.OrderGoodsBean> order_goods = model.getDatas().getOrder_goods();
            tvVegetName.setText(order_name);
            tvVegetTime.setText(model.getDatas().getStore_address());
            tvTotal.setText("共"+order_goods.size()+"件商品   小计：");
            tvTotalMoney.setText(model.getDatas().getOrder_amount()+"元");
            orderNumber.setText(model.getDatas().getOrder_sn());
            tvOrderTime.setText(model.getDatas().getAdd_time());
            tvPay.setText(model.getDatas().getPayment_code_text());
            tvBeizhu.setText(model.getDatas().getDeliver_explain());
            if(model.getDatas().getPayment_code().equals("online")) {
                tvPay.setText("在线支付");
            }else{
                tvPay.setText("余额支付");
            }
            adapter = new GoodsDetailsAdapter(this,order_goods);
            rlView.setAdapter(adapter);
        }
    }

    @Override
    public void commentOrder(BaseBean getInfo) {

    }

    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(this, "获取数据中...", false, null);
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

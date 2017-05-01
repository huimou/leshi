package com.wangjia.yijiale.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.adapter.GoodsDetailsAdapter;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.OrderDetails;
import com.wangjia.yijiale.event.StatusBarEvent;
import com.wangjia.yijiale.iview.OrderDetailsActivityView;
import com.wangjia.yijiale.presenter.DetailsOrderDetailsPresenter;
import com.wangjia.yijiale.presenter.impl.OrderActivityDetailsImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.views.CustomProgress;
import com.wangjia.yijiale.views.NoScrollRecycleView;
import com.wangjia.yijiale.views.RoundAngleImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 评论
 */
public class CommentStoreActivity extends AppCompatActivity implements OrderDetailsActivityView {


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
    @Bind(R.id.main_homeRadioBut)
    RadioButton mainHomeRadioBut;
    @Bind(R.id.main_questionRadioBut)
    RadioButton mainQuestionRadioBut;
    @Bind(R.id.main_findRadioBut)
    RadioButton mainFindRadioBut;
    @Bind(R.id.main_radioGroup)
    RadioGroup mainRadioGroup;
    @Bind(R.id.chaping_liyou_et)
    EditText chapingLiyouEt;
    @Bind(R.id.chaping_liyou_tv)
    TextView chapingLiyouTv;
    private int order_id;//传回来的订单id
    DetailsOrderDetailsPresenter detailsOrderDetailsPresenter;
    GoodsDetailsAdapter adapter;
    private static final String TAG = "DetailOrderActivity";
    private String order_name;
    private int store_desccredit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_store);
        ButterKnife.bind(this);
        order_id = getIntent().getIntExtra("order_id", 0);
        order_name = getIntent().getStringExtra("order_name");
        Log.d(TAG, "onCreate: " + order_id);
        new Titlebulder(this).setTitleName("评论")
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
        detailsOrderDetailsPresenter = new OrderActivityDetailsImpl(this, this);
        detailsOrderDetailsPresenter.getData(YiApplication.getInstance().getToken(), order_id);
    }

    @Override
    public void getData(OrderDetails model) {
        if (model != null) {
            Picasso.with(this).load(model.getDatas().getStore_avatar()).into(rivImage);
            List<OrderDetails.DatasBean.OrderGoodsBean> order_goods = model.getDatas().getOrder_goods();
            tvVegetName.setText(order_name);
            tvVegetTime.setText(model.getDatas().getStore_address());

            adapter = new GoodsDetailsAdapter(this, order_goods);
            rlView.setAdapter(adapter);

            mainRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId == R.id.main_homeRadioBut){
                        //好评
                        store_desccredit =5;
                    }else  if(checkedId == R.id.main_questionRadioBut){
                        //中评
                        store_desccredit =4;
                    }else  if(checkedId == R.id.main_findRadioBut){
                        //差评
                        store_desccredit =3;
                    }
                }
            });
        }
    }

    @Override
    public void commentOrder(BaseBean getInfo) {
        if(getInfo.getCode()== Constants.RESPONSE_SUCCESS){
            RxBus.getDefault().send(new StatusBarEvent("", "Update_data", 1));
            ToastUtils.showToast(CommentStoreActivity.this,getInfo.getMsg());
            finish();
        }
    }

    @OnClick({R.id.chaping_liyou_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chaping_liyou_tv:
                //评论
                detailsOrderDetailsPresenter.commentOrder(YiApplication.getInstance().getToken(),order_id,store_desccredit,5,5,5);

                break;



        }
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

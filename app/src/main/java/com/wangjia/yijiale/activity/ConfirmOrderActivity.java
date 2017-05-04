package com.wangjia.yijiale.activity;


/**
 * 确认订单
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.adapter.ConfirmListViewAdapter;
import com.wangjia.yijiale.address.AddressManageActivity;
import com.wangjia.yijiale.entity.AddressManageList;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.entity.SubmitSteOneBean;
import com.wangjia.yijiale.entity.SubmitSteTwoBean;
import com.wangjia.yijiale.iview.ConfirmOrderActivityView;
import com.wangjia.yijiale.presenter.impl.ConfirmOrderActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.views.CustomProgress;
import com.wangjia.yijiale.views.NoAlphRecycleScrollview;
import com.wangjia.yijiale.views.NoScrollRecycleView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmOrderActivity extends AppCompatActivity implements ConfirmOrderActivityView {
    @Bind(R.id.confirm_title)
    LinearLayout confirmTitle;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.phone_tv)
    TextView phoneTv;
    @Bind(R.id.address_tv)
    TextView addressTv;
    @Bind(R.id.update_address_rl)
    RelativeLayout updateAddressRl;
    @Bind(R.id.goods_norv)
    NoScrollRecycleView goodsNorv;
    @Bind(R.id.input_messages_ed)
    EditText inputMessagesEd;
    @Bind(R.id.scrollView)
    NoAlphRecycleScrollview scrollView;
    @Bind(R.id.choice_finish)
    RelativeLayout choiceFinish;
    @Bind(R.id.heji_tv)
    TextView hejiTv;
    @Bind(R.id.price_totle_tv)
    TextView priceTotleTv;
    @Bind(R.id.has_buy_num_tv)
    TextView has_buy_num_tv;
    @Bind(R.id.heji_rl)
    RelativeLayout hejiRl;
    @Bind(R.id.bottom)
    RelativeLayout bottom;
    @Bind(R.id.biaozhu_tv)
    TextView biaozhuTv;
    private Intent intent;
    private ConfirmOrderActivityPresenterImpl confirmOrderActivityPresenterImpl;
    private String store_id;
    private AddressManageList getInfo;
    String cart_id = "";
    private SubmitSteOneBean submitSteOneBean;
    private String my_goods_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        intent = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
//        goods_id = getIntent().getStringExtra("goods_id");
        store_id = getIntent().getStringExtra("store_id");
        confirmOrderActivityPresenterImpl = new ConfirmOrderActivityPresenterImpl(this, ConfirmOrderActivity.this);
//        //获取默认地址
//        confirmOrderActivityPresenterImpl.getDefaultAddress(YiApplication.getInstance().getToken());
//        //购物车列表
        confirmOrderActivityPresenterImpl.getData(YiApplication.getInstance().getToken(), store_id);
    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("确认订单")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        //        获取确认订单页面数据
//        confirmOrderActivityPresenterImpl.submitOrderForOnce(YiApplication.getInstance().getToken(), store_id, 1,
//                cart_id, "");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.choice_finish, R.id.update_address_rl})
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.choice_finish:
                //提交订单
                if(!StringFunction.isNotNull(biaozhuTv.getText().toString().trim())){
                    ToastUtils.showToast(ConfirmOrderActivity.this,"亲，你没有收货地址，请先填写收货地址！");
                    return;
                }

                if (submitSteOneBean != null && submitSteOneBean.getDatas() != null) {
                    confirmOrderActivityPresenterImpl.submitOrderForTwo(YiApplication.getInstance().getToken(), my_goods_id, 1,
                            cart_id, submitSteOneBean.getDatas().getAddress_info().getAddress_id() + "", inputMessagesEd.getText().toString(), "online",
                            submitSteOneBean.getDatas().getVat_hash(), submitSteOneBean.getDatas().getAddress_api().getAllow_offpay() + "",
                            submitSteOneBean.getDatas().getAddress_api().getOffpay_hash(), submitSteOneBean.getDatas().getAddress_api().getOffpay_hash_batch(),
                            submitSteOneBean.getDatas().getAddress_info().getCity_id() + "");
                }

                break;
            case R.id.update_address_rl:
                Intent intent = new Intent(ConfirmOrderActivity.this, AddressManageActivity.class);
                startActivity(intent);

                //更换地址
                break;
        }
    }


    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(ConfirmOrderActivity.this, "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        CustomProgress.dissmiss();
    }


    @Override
    public void getData(Cart model) {
        for (int i = 0; i < model.getDatas().getCart_list().size(); i++) {
            cart_id += model.getDatas().getCart_list().get(i).getCart_id() + "|" + model.getDatas().getCart_list().get(i).getGoods_sum() + ",";
            my_goods_id += model.getDatas().getCart_list().get(i).getGoods_id() + "|" + model.getDatas().getCart_list().get(i).getGoods_sum() + ",";
        }
        if (StringFunction.isNotNull(cart_id)) {
            cart_id = cart_id.substring(0, cart_id.length() - 1);
            my_goods_id = my_goods_id.substring(0, my_goods_id.length() - 1);
            //        获取确认订单页面数据
            confirmOrderActivityPresenterImpl.submitOrderForOnce(YiApplication.getInstance().getToken(), store_id, 1,
                    cart_id, "");
        }

    }

    @Override
    public void getDefaultAddress(AddressManageList getInfo) {


    }

    @Override
    public void submitOrderForOnce(final SubmitSteOneBean getInfo) {
        submitSteOneBean = getInfo;
        if (getInfo.getCode() == Constants.RESPONSE_SUCCESS) {
            //地址
            if (getInfo.getDatas() != null && getInfo.getDatas().getAddress_info() != null) {
                SubmitSteOneBean.DatasBean.AddressInfoBean address_info = getInfo.getDatas().getAddress_info();
                if (StringFunction.isNotNull(address_info.getTrue_name())) {
                    name.setText(address_info.getTrue_name());
                }

                if (StringFunction.isNotNull(address_info.getTel_phone())) {
                    phoneTv.setText(address_info.getTel_phone());
                }

                if (StringFunction.isNotNull(address_info.getAddress())) {

                    addressTv.setText(address_info.getArea_info() + address_info.getAddress());
                    biaozhuTv.setText("(确认订单后请尽快付款，过时订单将自动取消)");
                }

            }

            //商品列表
            ConfirmListViewAdapter confirmListViewAdapter = new ConfirmListViewAdapter(ConfirmOrderActivity.this,
                    getInfo.getDatas().getStore_cart_list().get(0).getGoods_list());
            goodsNorv.setAdapter(confirmListViewAdapter);
            confirmListViewAdapter.setmOnItemClickListener(new ConfirmListViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    Intent intent = new Intent(ConfirmOrderActivity.this, GoodsDetailActivity.class);
                    intent.putExtra("goods_id", getInfo.getDatas().getStore_cart_list().get(0).getGoods_list().get(postion).getGoods_id());
                    intent.putExtra("store_id", String.valueOf(getInfo.getDatas().getStore_cart_list().get(0).getGoods_list().get(postion).getStore_id()));
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int postion) {

                }
            });
            has_buy_num_tv.setText("共" + getInfo.getDatas().getStore_cart_list().get(0).getGoods_list().size() +
                    "件商品 小计：￥" + getInfo.getDatas().getOrder_amount());
            priceTotleTv.setText("￥" + getInfo.getDatas().getOrder_amount());

            cart_id = "";
            my_goods_id = "";
            for (int i = 0; i < getInfo.getDatas().getStore_cart_list().get(0).getGoods_list().size(); i++) {
                SubmitSteOneBean.DatasBean.StoreCartListBean.GoodsListBean goodsListBean = getInfo.getDatas().getStore_cart_list().get(0).getGoods_list().get(i);
                cart_id += goodsListBean.getCart_id() + "|" + goodsListBean.getGoods_num() + ",";
                my_goods_id += goodsListBean.getGoods_id() + "|" + goodsListBean.getGoods_num() + ",";
            }
            if (StringFunction.isNotNull(cart_id)) {
                cart_id = cart_id.substring(0, cart_id.length() - 1);
                my_goods_id = my_goods_id.substring(0, my_goods_id.length() - 1);
            }
        }else{
            ToastUtils.showToast(ConfirmOrderActivity.this,getInfo.getMsg());
        }
    }

    @Override
    public void submitOrderForTwo(SubmitSteTwoBean getInfo) {
        if (getInfo.getCode() == Constants.RESPONSE_SUCCESS) {
            Intent intent = new Intent(ConfirmOrderActivity.this, ZhifuActivity.class);
            intent.putExtra("pay_sn", getInfo.getDatas().getPay_sn());
            startActivityForResult(intent,10001);
        } else {
            ToastUtils.showToast(ConfirmOrderActivity.this, getInfo.getMsg());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK) {
            switch (requestCode) {
                case 10001:
                    finish();
                    break;
            }
        }
    }
}

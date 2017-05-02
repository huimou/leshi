package com.wangjia.yijiale.activity;


/**
 * 商品详情
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.adapter.FragmentTabAdapter;
import com.wangjia.yijiale.adapter.NetworkImageHolderView;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.GoodsDetailInfo;
import com.wangjia.yijiale.event.StatusBarEvent;
import com.wangjia.yijiale.iview.GoodsDetailActivityView;
import com.wangjia.yijiale.presenter.GoodsDetailActivityPresenter;
import com.wangjia.yijiale.presenter.impl.GoodsDetailActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.views.CartCartWindow;
import com.wangjia.yijiale.views.CustomProgress;
import com.wangjia.yijiale.views.MyWebView;
import com.wangjia.yijiale.views.NoAlphRecycleScrollview;
import com.wangjia.yijiale.views.ProductsPopupWindow;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

public class GoodsDetailActivity extends AppCompatActivity implements CartCartWindow.OnItemClickListener,
        CartCartWindow.OnPopupWindowDismiss, GoodsDetailActivityView {

    /**
     * Called when the activity is first created.
     */
    public List<Fragment> fragments;
    FragmentTabAdapter tabAdapter;
    @Bind(R.id.titlepg_right_iv)
    ImageView titlepgRightIv;
    @Bind(R.id.cycleView)
    ConvenientBanner cycleView;
    @Bind(R.id.goods_name_tv)
    TextView goodsNameTv;
    @Bind(R.id.goods_price_tv)
    TextView goodsPriceTv;
    @Bind(R.id.mark_goods_price_tv)
    TextView markGoodsPriceTv;
    @Bind(R.id.chooes_guize_iv)
    ImageView chooesGuizeIv;
    @Bind(R.id.goods_MyWebView)
    MyWebView goodsMyWebView;
    @Bind(R.id.scrollView)
    NoAlphRecycleScrollview scrollView;
    @Bind(R.id.choice_finish)
    RelativeLayout choiceFinish;
    @Bind(R.id.cart_add)
    ImageView cartAdd;
    @Bind(R.id.bottom)
    RelativeLayout bottom;
    @Bind(R.id.all_choice_layout)
    LinearLayout allChoiceLayout;
    @Bind(R.id.chooes_guize_rl)
    RelativeLayout chooesGuizeRl;
    @Bind(R.id.price_totle_tv)
    TextView price_totle_tv;
    private CartCartWindow addGoodsCartWindow;
    boolean isClickBuy = false;
    private Cart cart;
    public Subscription rxSubscription;
    private GoodsDetailActivityPresenter detailActivityPresenter;
    private boolean flag = true;
    private int position = 0;
    //1收藏  0取消
    int is_favorate = 0;
    private Intent intent;

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //chooseTime.setText((String) msg.obj);
                    Log.i("TAG", (String) msg.obj);
                    break;
                case 10:
                    //清空购物车
                    detailActivityPresenter.clearShopping(String.valueOf(store_id), YiApplication.getInstance().getToken());
                    break;
                case 2:
                    //有属性
                    addGoodsCartWindow = new CartCartWindow(GoodsDetailActivity.this, mHandler, cart);
                    addGoodsCartWindow.setOnItemClickListener(GoodsDetailActivity.this);
                    addGoodsCartWindow.setOnPopupWindowDismissListener(GoodsDetailActivity.this);
                    break;
                default:
                    break;
            }
        }

    };
    private String goods_id;
    private ProductsPopupWindow contentWindow;
    private GoodsDetailInfo model;
    private String store_id;
    //得到选择的属性集合
    private String[] choose_cat_strings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        intent = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        rxSubscription = RxBus.getDefault().toObservable(StatusBarEvent.class)
                .subscribe(new Action1<StatusBarEvent>() {
                    @Override
                    public void call(StatusBarEvent statusBarEvent) {
                        if (statusBarEvent.getName().equals("ProductFragmentCart")) {
                            if (statusBarEvent.getId().equals("add")) {
                                flag = true;
                                position = statusBarEvent.getI();
                                detailActivityPresenter.changeCartNum(cart.getDatas().getCart_list().get(position).getGoods_id()
                                        , String.valueOf(Integer.parseInt(cart.getDatas().getCart_list().get(position).getGoods_sum()) + 1)
                                        , YiApplication.getInstance().getToken());
                            } else {
                                position = statusBarEvent.getI();
                                flag = false;
                                detailActivityPresenter.changeCartNum(cart.getDatas().getCart_list().get(position).getGoods_id()
                                        , String.valueOf(Integer.parseInt(cart.getDatas().getCart_list().get(position).getGoods_sum()) - 1)
                                        , YiApplication.getInstance().getToken());
                            }
                        } else if (statusBarEvent.getName().equals("choose_cat")) {
                            //得到选择的属性值

                            String id = statusBarEvent.getId();
                            choose_cat_strings[Integer.parseInt(id)] = statusBarEvent.getI() + "";
                        } else if (statusBarEvent.getName().equals("add_cart")) {
                            //加入购物车
                            int statusBarEventI = statusBarEvent.getI();
                            String type = "";
                            for (int i = 0; i < choose_cat_strings.length; i++) {
                                if (!StringFunction.isNotNull(choose_cat_strings[i])) {
                                    ToastUtils.showToast(GoodsDetailActivity.this, "亲，你商品属性尚未选择完，请先选择完，在加入购物车！");
                                    return;
                                } else {
                                    type += choose_cat_strings[i];
                                    if (i < choose_cat_strings.length - 1) {
                                        type += "|";
                                    }
                                }
                            }
                            contentWindow.dismiss();
                            for (int i = 0; i < model.getDatas().getGoods_info().getSpec_list().size(); i++) {
                                if (model.getDatas().getGoods_info().getSpec_list().get(i).getSign().equals(type)) {
                                    String goods_id = model.getDatas().getGoods_info().getSpec_list().get(i).getGoods_id();
                                    detailActivityPresenter.changeNum(goods_id, statusBarEventI + "", YiApplication.getInstance().getToken());
                                    break;
                                }
                            }

                        }
                    }
                });
        goods_id = getIntent().getStringExtra("goods_id");
        store_id = getIntent().getStringExtra("store_id");
        detailActivityPresenter = new GoodsDetailActivityPresenterImpl(this, GoodsDetailActivity.this);
        //获取商品详情
        detailActivityPresenter.getGoodsData(YiApplication.getInstance().getToken(), goods_id);
        //购物车列表
        detailActivityPresenter.getData(YiApplication.getInstance().getToken(), store_id);

    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("商品详情")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).setRightImage(R.mipmap.collection_c);
    }

    /**
     * 给轮播图设置图片路径
     */
    public void setLoopView(List<String> mb_sliders) {
        //初始化商品图片轮播
        cycleView.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, mb_sliders);

        cycleView.setPageIndicator(new int[]{R.drawable.shape_item_index_gray, R.drawable.shape_item_index_blue});
        cycleView.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        cycleView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                L.i("" + position);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        cycleView.startTurning(4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        cycleView.stopTurning();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rxSubscription != null) {
            rxSubscription.unsubscribe();
        }
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.cart_add, R.id.titlepg_right_iv, R.id.chooes_guize_rl, R.id.choice_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cart_add:
                if (addGoodsCartWindow != null) {
                    isClickBuy = true;
                    setBackgroundBlack(allChoiceLayout, 0);
                    addGoodsCartWindow.showAsDropDown(view);
                }
                break;

            case R.id.chooes_guize_rl:
                // 普通商品
                if (model != null && model.getDatas() != null && model.getDatas().getGoods_info() != null) {
                    contentWindow = new ProductsPopupWindow(GoodsDetailActivity.this, model.getDatas().getGoods_info());
                    // 显示窗口
                    contentWindow.showAtLocation(chooesGuizeRl, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                }
                break;

            case R.id.titlepg_right_iv:
                //收藏
                if (StringFunction.isNotNull(YiApplication.getInstance().getToken())) {
                    detailActivityPresenter.setGoodsCollect(goods_id, is_favorate);
                } else {
                    ToastUtils.showToast(GoodsDetailActivity.this, "亲，你尚未登录，请先登录在操作！");
                }
                break;
            case R.id.choice_finish:
                //提交订单
                if (StringFunction.isNotNull(cart) && StringFunction.isNotNull(cart.getDatas()) &&
                        StringFunction.isNotNull(cart.getDatas().getCart_list()) && cart.getDatas().getCart_list().size() > 0) {
                    Intent intent = new Intent(GoodsDetailActivity.this, ConfirmOrderActivity.class);
                    intent.putExtra("store_id", store_id);
                    startActivity(intent);
                } else {
                    ToastUtils.showToast(GoodsDetailActivity.this,"亲，你尚未购买任何东西，不能提交哦！");
                }

                break;
        }
    }

    //点击弹窗的确认按钮的响应
    @Override
    public void onClickOKPop(int payNum, int[] atts) {
        setBackgroundBlack(allChoiceLayout, 1);
        //加入购物车
        /*String s = (String) SPUtils.get(getActivity(), Constants.TOKEN, "");
        addGoodsCart(s, data.getData().getGoods().getGoods_id(),atts,payNum);*/
        /*if (isClickBuy) {
            //如果之前是点击的立即购买，那么就跳转到立即购物界面
            Intent intent = new Intent(ConfirmOrderActivity.this, AddAddressChooseActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "添加到购物车成功", Toast.LENGTH_SHORT).show();
        }*/
    }


    /**
     * 控制背景变暗 0变暗 1变亮
     */
    public void setBackgroundBlack(View view, int what) {
        switch (what) {
            case 0:
                view.setVisibility(View.VISIBLE);
                break;
            case 1:
                view.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onPopupWindowDisss() {
        setBackgroundBlack(allChoiceLayout, 1);
        //更新购物车列表
        detailActivityPresenter.getData(YiApplication.getInstance().getToken(), String.valueOf(store_id));
    }


    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(GoodsDetailActivity.this, "获取数据中...", false, null);
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
    public void getGoodsData(GoodsDetailInfo model) {

        try {
            if (model.getCode() == Constants.RESPONSE_SUCCESS && model != null) {
                this.model = model;
                List<String> goods_image = model.getDatas().getGoods_info().getGoods_image();
                if (goods_image != null) {
                    setLoopView(goods_image);
                }
                if(model.getDatas().getGoods_info()!=null && model.getDatas().getGoods_info().getSpec_value()!=null
                        && model.getDatas().getGoods_info().getSpec_value().size()>0) {
                    choose_cat_strings = new String[model.getDatas().getGoods_info().getSpec_value().size()];
                }else{
                    choose_cat_strings = new String[0];
                }
                if (StringFunction.isNotNull(model.getDatas().getGoods_info())) {
                    goodsNameTv.setText(model.getDatas().getGoods_info().getGoods_name());
                    goodsPriceTv.setText("￥ " + model.getDatas().getGoods_info().getGoods_price() + "元");
                    markGoodsPriceTv.setText("市场价：￥" + model.getDatas().getGoods_info().getGoods_marketprice() + "元");
                    if (model.getDatas().getGoods_info().getIs_favorate() == 0) {
                        titlepgRightIv.setImageResource(R.mipmap.collection_c);
                    } else if (model.getDatas().getGoods_info().getIs_favorate() == 1) {
                        titlepgRightIv.setImageResource(R.mipmap.collection_nc);
                    }
                    is_favorate = model.getDatas().getGoods_info().getIs_favorate();
                    goodsMyWebView.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
                    if (StringFunction.isNotNull(model.getDatas()) &&
                            StringFunction.isNotNull(model.getDatas().getGoods_info()) &&
                            StringFunction.isNotNull(model.getDatas().getGoods_info().getGoods_body())) {
                        goodsMyWebView.loadData(model.getDatas().getGoods_info().getGoods_body(), "text/html; charset=UTF-8", null);//这种写法可以正确解码
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void collectGoodsResult(BaseBean model) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            if (model.getMsg().contains("删除")) {
                is_favorate = 0;
                titlepgRightIv.setImageResource(R.mipmap.collection_c);
            } else {
                is_favorate = 1;
                titlepgRightIv.setImageResource(R.mipmap.collection_nc);
            }
        }
        ToastUtils.showToast(GoodsDetailActivity.this, model.getMsg());
    }

    @Override
    public void getData(Cart model) {
        this.cart = model;
//        addGoodsCartWindow = new CartCartWindow(GoodsDetailActivity.this, mHandler, cart);
//        addGoodsCartWindow.setOnItemClickListener(GoodsDetailActivity.this);
//        addGoodsCartWindow.setOnPopupWindowDismissListener(GoodsDetailActivity.this);
        if (model.getCode() == 200) {
            mHandler.sendMessage(mHandler.obtainMessage(2));
            price_totle_tv.setText("￥" + model.getDatas().getSum_price());
        }
    }

    @Override
    public void changeCartNum(ChangeNum getInfo) {
        if (model.getCode() == 200) {
            if (flag) {
                cart.getDatas().getCart_list().get(position).
                        setGoods_sum(String.valueOf(Integer.parseInt(cart.getDatas().getCart_list().get(position).getGoods_sum()) + 1));
                addGoodsCartWindow.setData(cart, position);
            } else {
                cart.getDatas().getCart_list().get(position).
                        setGoods_sum(String.valueOf(Integer.parseInt(cart.getDatas().getCart_list().get(position).getGoods_sum()) - 1));
                if (cart.getDatas().getCart_list().get(position).getGoods_sum().equals("0")) {
                    cart.getDatas().getCart_list().remove(position);
                }
                addGoodsCartWindow.setData(cart, position);
            }
        }
    }

    @Override
    public void changeNum(ChangeNum getInfo) {
        //更新购物车列表
        detailActivityPresenter.getData(YiApplication.getInstance().getToken(), String.valueOf(store_id));
    }

    @Override
    public void clearShopping(BaseBean getInfo) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            addGoodsCartWindow.dissmiss();
            //购物车列表
            cart = null;
            detailActivityPresenter.getData(YiApplication.getInstance().getToken(), String.valueOf(store_id));
        }
//        ToastUtils.showToast(GoodsDetailActivity.this, model.getMsg());
    }

}

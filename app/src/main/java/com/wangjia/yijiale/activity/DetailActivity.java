package com.wangjia.yijiale.activity;


/**
 * 商家详情
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
import com.wangjia.yijiale.entity.StoreDetailInfo;
import com.wangjia.yijiale.event.StatusBarEvent;
import com.wangjia.yijiale.iview.DetailActivityView;
import com.wangjia.yijiale.otherfragment.CommentListFragment;
import com.wangjia.yijiale.otherfragment.ProductListFragment;
import com.wangjia.yijiale.otherfragment.StoreDetailFragment;
import com.wangjia.yijiale.presenter.DetailActivityPresenter;
import com.wangjia.yijiale.presenter.impl.DetailActivityPresenterImpl;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.utils.Utils;
import com.wangjia.yijiale.views.CartCartWindow;
import com.wangjia.yijiale.views.CustomProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

public class DetailActivity extends AppCompatActivity implements CartCartWindow.OnItemClickListener,
        CartCartWindow.OnPopupWindowDismiss, DetailActivityView {
    @Bind(R.id.bottom)
    RelativeLayout bottom;
    @Bind(R.id.store_name_tv)
    TextView storeNameTv;
    @Bind(R.id.price_totle_tv)
    TextView price_totle_tv;
    @Bind(R.id.scan_erweima_iv)
    ImageView scanErweimaIv;
    @Bind(R.id.titlepg_right_iv)
    ImageView titlepgRightIv;
    private Intent i;
    @Bind(R.id.cycleView)
    ConvenientBanner cycleView;
    @Bind(R.id.order_top_tabs)
    RadioGroup orderTopTabs;
    @Bind(R.id.all_choice_layout)
    LinearLayout allChoiceLayout;
    /**
     * Called when the activity is first created.
     */
    public List<Fragment> fragments;
    FragmentTabAdapter tabAdapter;
    private CartCartWindow addGoodsCartWindow;
    boolean isClickBuy = false;
    private Cart cart;
    public Subscription rxSubscription;
    private DetailActivityPresenter detailActivityPresenter;
    private boolean flag = true;
    private int position = 0;
    //店铺ID
    private int store_id;
    //0收藏  1取消
    int is_favorate = 0;

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
                    addGoodsCartWindow = new CartCartWindow(DetailActivity.this, mHandler, cart);
                    addGoodsCartWindow.setOnItemClickListener(DetailActivity.this);
                    addGoodsCartWindow.setOnPopupWindowDismissListener(DetailActivity.this);
                    break;
                default:
                    break;
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        i = new Intent();
        ButterKnife.bind(this);
        store_id = getIntent().getIntExtra("store_id", 0);
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
//                                detailActivityPresenter.getData(YiApplication.getInstance().getToken(), String.valueOf(store_id));
                            } else {
                                position = statusBarEvent.getI();
                                flag = false;
                                detailActivityPresenter.changeCartNum(cart.getDatas().getCart_list().get(position).getGoods_id()
                                        , String.valueOf(Integer.parseInt(cart.getDatas().getCart_list().get(position).getGoods_sum()) - 1)
                                        , YiApplication.getInstance().getToken());
//                                detailActivityPresenter.getData(YiApplication.getInstance().getToken(), String.valueOf(store_id));
                            }
                        } else if (statusBarEvent.getName().equals("UpdateProductFragmentCart")) {
                            //更新购物车列表
                            detailActivityPresenter.getData(YiApplication.getInstance().getToken(), String.valueOf(store_id));
                        }
                    }
                });
        detailActivityPresenter = new DetailActivityPresenterImpl(this, DetailActivity.this);
        cart = new Cart();
        fragments = new ArrayList<Fragment>();

        ProductListFragment productListFragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("store_id", store_id);
        productListFragment.setArguments(bundle);
        fragments.add(productListFragment);

        CommentListFragment commentListFragment = new CommentListFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("store_id", store_id);
        commentListFragment.setArguments(bundle2);
        fragments.add(commentListFragment);

        StoreDetailFragment storeDetailFragment = new StoreDetailFragment();
        Bundle bundles = new Bundle();
        bundles.putInt("store_id", store_id);
        storeDetailFragment.setArguments(bundles);
        fragments.add(storeDetailFragment);

        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.order_content, orderTopTabs, 0);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                L.e("Extra---- " + index + " checked!!! ");
                L.i(index + "");
                if (index == 0) {
                    bottom.setVisibility(View.VISIBLE);
                } else {
                    bottom.setVisibility(View.GONE);
                }
            }
        });
    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("商家店铺")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
//                .setRightImage(R.mipmap.collection_c);


        //购物车列表
        detailActivityPresenter.getData(YiApplication.getInstance().getToken(), String.valueOf(store_id));
        //店铺详情
        detailActivityPresenter.getStoreData(String.valueOf(store_id));
    }

    /**
     * 给轮播图设置图片路径
     */
    public void setLoopView(List<StoreDetailInfo.DatasBean.MbSlidersBean> mb_sliders) {
        List<String> imgUrls = new ArrayList<>();
        for (StoreDetailInfo.DatasBean.MbSlidersBean mbSlidersBean : mb_sliders) {
            imgUrls.add(Utils.getImageUrl(mbSlidersBean.getImgUrl()));
        }
        //初始化商品图片轮播
        cycleView.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, imgUrls);

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
        //更新购物车列表
        detailActivityPresenter.getData(YiApplication.getInstance().getToken(), String.valueOf(store_id));
        //更新商品列表
        RxBus.getDefault().send(new StatusBarEvent("del", "UpdateProductFragmentList", 1));
    }

    @Override
    public void onPause() {
        super.onPause();
        cycleView.stopTurning();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxSubscription.unsubscribe();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.choice_finish, R.id.cart_add, R.id.titlepg_right_iv, R.id.scan_erweima_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cart_add:
                if (addGoodsCartWindow != null) {
                    isClickBuy = true;
                    setBackgroundBlack(allChoiceLayout, 0);
                    addGoodsCartWindow.showAsDropDown(view);
                }
                break;
            case R.id.titlepg_right_iv:
                //收藏
                if (StringFunction.isNotNull(YiApplication.getInstance().getToken())) {
                    detailActivityPresenter.setCollect(String.valueOf(store_id), is_favorate);
                } else {
                    ToastUtils.showToast(DetailActivity.this, "亲，你尚未登录，请先登录在操作！");
                }
                break;
            case R.id.choice_finish:
                //提交订单
                if (StringFunction.isNotNull(cart) && StringFunction.isNotNull(cart.getDatas()) &&
                        StringFunction.isNotNull(cart.getDatas().getCart_list()) && cart.getDatas().getCart_list().size() > 0) {
                    Intent intent = new Intent(DetailActivity.this, ConfirmOrderActivity.class);
                    intent.putExtra("store_id", String.valueOf(store_id));
                    startActivity(intent);
                } else {
                    ToastUtils.showToast(DetailActivity.this,"亲，你尚未购买任何东西，不能提交哦！");
                }

                break;
            case R.id.scan_erweima_iv:
                Intent intent = new Intent(DetailActivity.this, QrCodeActivity.class);
                intent.putExtra("store_id",store_id);
                startActivity(intent);
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
        //更新商品列表
        RxBus.getDefault().send(new StatusBarEvent("del", "UpdateProductFragmentList", 1));
    }

    @Override
    public void getData(Cart model) {
        //购物车列表
        cart = null;
        cart = model;
        if (model.getCode() == 200) {
            mHandler.sendMessage(mHandler.obtainMessage(2));
            price_totle_tv.setText("￥" + model.getDatas().getSum_price());
        }
    }

    @Override
    public void getStroeData(StoreDetailInfo model) {
        //店铺详情
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            List<StoreDetailInfo.DatasBean.MbSlidersBean> mb_sliders = model.getDatas().getMb_sliders();
            storeNameTv.setText(model.getDatas().getStore_name());
            is_favorate = model.getDatas().getIs_favorate();
            if (is_favorate == 0) {
                titlepgRightIv.setImageResource(R.mipmap.collection_c);
            } else if (is_favorate == 1) {
                titlepgRightIv.setImageResource(R.mipmap.collection_nc);
            }
            setLoopView(mb_sliders);

        }
//        ToastUtils.showToast(DetailActivity.this, model.getMsg());
    }

    @Override
    public void changeCartNum(ChangeNum model) {
        if (model.getCode() == 200) {
            if (flag) {
                cart.getDatas().getCart_list().get(position).
                        setGoods_sum(String.valueOf(Integer.parseInt(cart.getDatas().getCart_list().get(position).getGoods_sum()) + 1));
                addGoodsCartWindow.setData(cart, position);

            } else {
                cart.getDatas().getCart_list().get(position).
                        setGoods_sum(String.valueOf(Integer.parseInt(cart.getDatas().getCart_list().get(position).getGoods_sum()) - 1));
                if(cart.getDatas().getCart_list().get(position).getGoods_sum().equals("0")){
                    cart.getDatas().getCart_list().remove(position);
                }
                addGoodsCartWindow.setData(cart, position);
            }
        }
//        L.TShort(DetailActivity.this, model.getDatas());
    }

    @Override
    public void collectResult(BaseBean model) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            if (model.getMsg().contains("删除")) {
                is_favorate = 0;
                titlepgRightIv.setImageResource(R.mipmap.collection_c);
            } else {
                is_favorate = 1;
                titlepgRightIv.setImageResource(R.mipmap.collection_nc);
            }
        }
        ToastUtils.showToast(DetailActivity.this, model.getMsg());
    }

    @Override
    public void clearShopping(BaseBean model) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            addGoodsCartWindow.dissmiss();
            //购物车列表
            cart = null;
            detailActivityPresenter.getData(YiApplication.getInstance().getToken(), String.valueOf(store_id));
        }
//        ToastUtils.showToast(DetailActivity.this, model.getMsg());
    }

    @Override
    public void showProgressDialog() {
        if (!this.isFinishing()) {
            CustomProgress.showProgress(DetailActivity.this, "获取数据中...", false, null);
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

}

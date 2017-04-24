package com.wangjia.yijiale.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.adapter.SendAddressAdapter;
import com.wangjia.yijiale.entity.GoodsDetailInfo;
import com.wangjia.yijiale.event.StatusBarEvent;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.utils.ToastUtils;
import com.wangjia.yijiale.utils.Utils;

import static com.wangjia.yijiale.R.id.detail_pop_count;


public class ProductsPopupWindow extends PopupWindow implements
        OnClickListener, CompoundButton.OnCheckedChangeListener {

    private final ListView send_address_nlv;
    private View mMenuView;
    private String product_number;
    private String product_id;

    private String countnumber;

    private ImageView photoView;
    private TextView nameView, stockView, priceView, countView;
    private LinearLayout popLayout;
    private RadioButton nomeButton;
    private ImageView closeView;
    private RadioButton[][] mrRadioButtons;
    private RadioGroup[] mGroups;
    private ImageButton addButton, minusButton;
    private int[] selected;
    private Context mContext;

    private Dialog dialog;
    private View dialogView;
    private String realStock;
    private boolean flg = true;
    private int time = 1;
    private int isBuy = 0;
    public final static int SHOWPPW = 0;
    CallBack3 myCallBack;

    private View lineView;

    // 选择发货地址
    private ScrollGridView send_address_gv;

    private Button ouka_products_addcart_bt;
    private String send_address_id = "";
    private LinearLayout peisong_address_ll;
    private View popupwindowLayout;
    private ScrollView fh_address_sv;
    private LinearLayout skill_buy_ll;
    private GoodsDetailInfo.DatasBean.GoodsInfoBean model;

    public ProductsPopupWindow(Context context, GoodsDetailInfo.DatasBean.GoodsInfoBean model) {
        super(context);
        this.mContext = context;
        this.model = model;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mMenuView = inflater.inflate(R.layout.ouka_products_popupwindow, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
//        fh_address_sv = (ScrollView) mMenuView.findViewById(R.id.fh_address_sv);
        photoView = (ImageView) mMenuView.findViewById(R.id.detail_pop_photo);
        nameView = (TextView) mMenuView.findViewById(R.id.detail_pop_name);
        stockView = (TextView) mMenuView.findViewById(R.id.detail_pop_stock);
        priceView = (TextView) mMenuView.findViewById(R.id.detail_pop_price);
        popLayout = (LinearLayout) mMenuView.findViewById(R.id.detail_pop_layout);
        peisong_address_ll = (LinearLayout) mMenuView.findViewById(R.id.peisong_address_ll);
        skill_buy_ll = (LinearLayout) mMenuView.findViewById(R.id.skill_buy_ll);
        countView = (TextView) mMenuView.findViewById(detail_pop_count);
        ouka_products_addcart_bt = (Button) mMenuView.findViewById(R.id.ouka_products_addcart_bt);
        addButton = (ImageButton) mMenuView.findViewById(R.id.detail_pop_add);
        minusButton = (ImageButton) mMenuView.findViewById(R.id.detail_pop_minus);
        send_address_nlv = (ListView) mMenuView.findViewById(R.id.send_address_nlv);
        lineView = mMenuView.findViewById(R.id.detail_pop_line);


        addButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        ouka_products_addcart_bt.setOnClickListener(this);

        closeView = (ImageView) mMenuView.findViewById(R.id.detail_pop_close);
        closeView.setOnClickListener(this);


        // 初始化选择提货地址的控件
        initChoiseTypeView(context);
        // 初始化商品属性的控件
        initDate(context);

    }

    private void initChoiseTypeView(Context context) {
        // 动态改变窗口宽高
        handler.sendEmptyMessageDelayed(SHOWPPW, 60);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SHOWPPW:
//                    int fh_address_sv_height = fh_address_sv.getHeight();
//                    if (fh_address_sv_height > 600) {
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                LayoutParams.MATCH_PARENT, 600);
//                        fh_address_sv.setLayoutParams(params);
//                    }
                    break;

                default:
                    break;
            }
        }

        ;
    };

    private void initDate(Context context) {
        try {
            Glide.with(context).load(Utils.getImageUrl(model.getGoods_image().get(0))).error(R.mipmap.iconfont_wode).into(photoView);
            nameView.setText(model.getGoods_name());
            priceView.setText("￥ " + model.getGoods_price());
            final SendAddressAdapter adapter = new SendAddressAdapter(context, model.getSpec_value());
            send_address_nlv.setAdapter(adapter);
//            send_address_nlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view,
//                                        int position, long id) {
//
//                    adapter.chooceState(position);
//                    adapter.notifyDataSetChanged();
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_pop_close:
                dismiss();
                break;
            case R.id.detail_pop_add:
                int anInt = Integer.parseInt(countView.getText().toString());
                anInt += 1;
                countView.setText(String.valueOf(anInt));
                break;
            case R.id.detail_pop_minus:
                int anIntto = Integer.parseInt(countView.getText().toString());
                if (anIntto > 1) {
                    anIntto = anIntto - 1;
                    countView.setText(String.valueOf(anIntto));
                }else{
                    ToastUtils.showToast(mContext,"亲，数量已经最低了，不能在减了！");
                    countView.setText("1");
                }

                break;

            // 加入购物车
            case R.id.ouka_products_addcart_bt:

                RxBus.getDefault().send(new StatusBarEvent("", "add_cart", Integer.parseInt(countView.getText().toString())));
                break;


            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    /*
     * 回调按钮点击事件到Activity
     */
    public interface CallBack3 {
        public void click(View v, String send_address_id,
                          PopupWindow product_popupWindow);
    }


}

package com.wangjia.yijiale.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.adapter.CartListViewAdapter;
import com.wangjia.yijiale.adapter.GoodsAttrListAdapter;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.utils.L;


/**
 * 宝贝详情界面的弹窗
 *
 * @author http://yecaoly.taobao.com
 */
@SuppressLint("CommitPrefEdits")
public class CartCartWindow implements OnDismissListener,GoodsAttrListAdapter.OnParentOnChangeListener {
    private final TextView clear_goods_shopping;
    private PopupWindow popupWindow;
    private OnItemClickListener listener;
    private OnPopupWindowDismiss listener1;
    private final int ADDORREDUCE = 1;
    private Context context;
    //用来存放商品属性的ID
    /**
     * 保存选择的颜色的数据
     */
    // private String str_color = "";
    /**
     * 保存选择的类型的数据
     */
    // private String str_type = "";
    private Handler handler;
    private Cart cart;
    private RecyclerView recyclerView;
    private CartListViewAdapter cartListViewAdapter;
    private int atts[];

    private int num = 0;
    private int paynum=1;
//    private Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    Log.i("TAG", String.valueOf(msg.obj));
//                    num = Integer.parseInt(String.valueOf(msg.obj));
//                    break;
//                default:
//                    break;
//            }
//        };
//    };


    public CartCartWindow(Context context, final Handler handler, Cart cart) {
        this.context = context;
        this.handler = handler;
        this.cart = cart;
        View view = LayoutInflater.from(context).inflate(R.layout.cart_popwindow, null);

        L.i("c1");
        recyclerView = (RecyclerView) view.findViewById(R.id.goods_listview);
        clear_goods_shopping = (TextView) view.findViewById(R.id.clear_goods_shopping);
        clear_goods_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(10);
            }
        });
        cartListViewAdapter = new CartListViewAdapter(context, null);
        recyclerView.setAdapter(cartListViewAdapter);
        setOrientation1();
        popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //设置popwindow的动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
        setData(cart,-1);
        /*this.atts=new int[goodsAttr.getGoods_attr().size()];
        for(int i=0;i<goodsAttr.getGoods_attr().size();i++){
            if(goodsAttr.getGoods_attr().get(i).getValues().size()>0)
                this.atts[i]=goodsAttr.getGoods_attr().get(i).getValues().get(0).getSubid();
        }*/

        //getDate(goodsAttr.getGoods().getGoods_id(),this.atts,this.paynum);
    }

    /**
     * 设置RecycleView布局方式
     */
    public void setOrientation1() {
        //设置布局管理
        LinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        cartListViewAdapter.setmOnItemClickListener(new CartListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    public void setData(Cart cart,int position) {
        cartListViewAdapter.setDatas(cart,position);
            /*goods_price.setText(String.valueOf(goodsAttr.getGoods().getShop_price()));
            goods_stock.setText(goodsAttr.getGoods().getGoods_number());
            num = Integer.parseInt(goodsAttr.getGoods().getGoods_number());
            Glide.with(context).load(goodsAttr.getGoods().getShop_logo())
                    .placeholder(R.drawable.image_general).into(iv_adapter_grid_pic);*/

    }

    //listview点击回调
    @Override
    public void OnChange(int[] atts) {
        /*this.atts=atts;*/
        //getDate(goodsAttr.getGoods().getGoods_id(),atts,paynum);
    }


    public interface OnItemClickListener {
        /**
         * 设置点击确认按钮时监听接口
         */
        public void onClickOKPop(int payNum, int[] atts);
    }

    /**
     * 设置监听
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    /**
     * 设置监听
     */
    public void setOnPopupWindowDismissListener(OnPopupWindowDismiss listener1) {
        this.listener1 = listener1;
    }


    public interface OnPopupWindowDismiss {
        /**
         * 设置popupWindow消失监听接口
         */
        public void onPopupWindowDisss();
    }

    // 当popWindow消失时响应
    @Override
    public void onDismiss() {
        listener1.onPopupWindowDisss();
    }

    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent) {
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
    }

    /**
     * 消除弹窗
     */
    public void dissmiss() {
        popupWindow.dismiss();
    }



    /**
     * 根据属性ID获取数据
     *//*
    public void getDate(String goodId, int[] atts, int number) {
        if(atts==null){
            Toast.makeText(context,"没有获取到商品属性", Toast.LENGTH_SHORT).show();
            return;
        }
        if(number==0){
            number=1;
        }

        String s_atts=JsonUtil.getGsonInstance().toJson(atts);
        Log.e("Test","json:"+s_atts);
        Log.e("Test","id"+goodId);
        Log.e("Test","number"+number);

        CustomProgress.showProgress(context, "加载中...", false, null);
        OkHttpUtils
                .post()//
                .url(URIUnifiedList.getPrice)//
                .addParams("goods_id", goodId)
                .addParams("number", number+"")
                .addParams("attr", s_atts)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        CustomProgress.dissmiss();
                    }
                    @Override
                    public void onResponse(String response) {
                        CustomProgress.dissmiss();
                        L.e("Test：" + response);
                        GetPriceEntity entity =  JsonUtil.getGsonInstance().fromJson(response,GetPriceEntity.class);
                        if(entity.getCode()!=200){
                            Toast.makeText(context,entity.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            setUpDate(entity.getData());
                        }
                    }
                });
    }

    *//**
     * 当用户点击商品属性时刷新价格和库存
     * @param data
     *//*
    private void setUpDate(GetPriceEntity.DataBean data) {
        if(data==null){
            return;
        }
        goods_price.setText(data.getShop_price());
        goods_stock.setText(data.getCurrent_number());
        num = Integer.parseInt(data.getCurrent_number());
//        Glide.with(context).load(goodsAttr.getGoods().getShop_logo())
//                .placeholder(R.drawable.image_general).into(iv_adapter_grid_pic);
    }*/
}

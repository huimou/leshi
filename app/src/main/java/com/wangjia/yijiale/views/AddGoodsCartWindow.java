package com.wangjia.yijiale.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.adapter.GoodsAttrListAdapter;
import com.wangjia.yijiale.entity.GoodsDetail;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * 宝贝详情界面的弹窗
 *
 * @author http://yecaoly.taobao.com
 */
@SuppressLint("CommitPrefEdits")
public class AddGoodsCartWindow implements OnDismissListener, OnClickListener,GoodsAttrListAdapter.OnParentOnChangeListener {
    private TextView pop_ok, pop_num, pop_add, pop_reduce;
    private ImageView pop_del, iv_adapter_grid_pic;
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
    private GoodsDetail.DataBean goodsAttr1;
    private NoScrollListView goods_listview;
    private GoodsAttrListAdapter rightAdapter;
    private TextView goods_price, goods_stock;
    private int atts[];

    private int num = 0;
    private int paynum=1;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.i("TAG", String.valueOf(msg.obj));
                    goods_stock.setText(String.valueOf(msg.obj));
                    num = Integer.parseInt(String.valueOf(msg.obj));
                    break;
                default:
                    break;
            }
        };
    };


    public AddGoodsCartWindow(Context context, Handler handler, GoodsDetail.DataBean goodsAttr) {
        this.context = context;
        this.handler = handler;
        //this.goodsAttr = goodsAttr;
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_popwindow, null);
        pop_ok = (TextView) view.findViewById(R.id.pop_ok);
        pop_del = (ImageView) view.findViewById(R.id.pop_del);
        iv_adapter_grid_pic = (ImageView) view.findViewById(R.id.iv_adapter_grid_pic);
        goods_price = (TextView) view.findViewById(R.id.goods_price);
        goods_stock = (TextView) view.findViewById(R.id.goods_stock);
        pop_num = (TextView) view.findViewById(R.id.pop_num);
        pop_add = (TextView) view.findViewById(R.id.pop_add);
        pop_reduce = (TextView) view.findViewById(R.id.pop_reduce);
        pop_ok.setOnClickListener(this);
        pop_del.setOnClickListener(this);
        pop_add.setOnClickListener(this);
        pop_reduce.setOnClickListener(this);

        goods_listview = (NoScrollListView) view.findViewById(R.id.goods_listview);
        rightAdapter = new GoodsAttrListAdapter(context, goodsAttr,this);
        goods_listview.setAdapter(rightAdapter);
        popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //设置popwindow的动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
        /*setData();
        this.atts=new int[goodsAttr.getGoods_attr().size()];
        for(int i=0;i<goodsAttr.getGoods_attr().size();i++){
            if(goodsAttr.getGoods_attr().get(i).getValues().size()>0)
                this.atts[i]=goodsAttr.getGoods_attr().get(i).getValues().get(0).getSubid();
        }*/

        //getDate(goodsAttr.getGoods().getGoods_id(),this.atts,this.paynum);
    }

    public void setData() {

            /*goods_price.setText(String.valueOf(goodsAttr.getGoods().getShop_price()));
            goods_stock.setText(goodsAttr.getGoods().getGoods_number());
            num = Integer.parseInt(goodsAttr.getGoods().getGoods_number());
            Glide.with(context).load(goodsAttr.getGoods().getShop_logo())
                    .placeholder(R.drawable.image_general).into(iv_adapter_grid_pic);*/

    }

    //listview点击回调
    @Override
    public void OnChange(int[] atts) {
        this.atts=atts;
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pop_del:
                listener.onClickOKPop(paynum,atts);
                dissmiss();
                break;
            case R.id.pop_ok:
                listener.onClickOKPop(paynum,atts);
                dissmiss();
                break;
            case R.id.pop_add:
                if (Integer.parseInt(pop_num.getText().toString()) < num) {
                    String num_add = Integer.valueOf(pop_num.getText().toString()) + ADDORREDUCE + "";
                    pop_num.setText(num_add);
                    paynum= Integer.parseInt(num_add);
                    //getDate(goodsAttr.getGoods().getGoods_id(),atts,paynum);
                } else {
                    Toast.makeText(context, "不能超过最大产品数量", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.pop_reduce:
                if (!pop_num.getText().toString().equals("1")) {
                    String num_reduce = Integer.valueOf(pop_num.getText().toString()) - ADDORREDUCE + "";
                    pop_num.setText(num_reduce);
                    paynum= Integer.parseInt(num_reduce);
                    //getDate(goodsAttr.getGoods().getGoods_id(),atts,paynum);
                } else {
                    Toast.makeText(context, "购买数量不能低于1件", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
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

package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.activity.DetailOrderActivity;
import com.wangjia.yijiale.entity.MyOrder;
import com.wangjia.yijiale.views.RoundAngleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Samson on 2016/12/29.
 */

public class OrderAdapter extends RecyclerView.Adapter {

    LayoutInflater inflater;
    Context context;
    MyOrder datas;


    public OrderAdapter(MyOrder datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(MyOrder data) {
        this.datas = data;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order_list_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final MyOrder.DatasBean datasBean = datas.getDatas().get(position);
        viewHolder.orderNumber.setText("订单号：" + datasBean.getOrder_sn());
        viewHolder.orderStatus.setText(datasBean.getOrder_state_text());
        viewHolder.tvVegetName.setText(datasBean.getStore_name());
        viewHolder.tvVegetTime.setText("下单时间：" + datasBean.getAdd_time());
        viewHolder.tvTotalMoney.setText(datasBean.getOrder_amount() + "元");
        int order_state = datasBean.getOrder_state();
        Log.d("zhuangtai", order_state + "");
        Picasso.with(context).load(datasBean.getStore_avatar()).into(viewHolder.rivImage);
        viewHolder.ll_look_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailOrderActivity.class);
                intent.putExtra("order_id", datasBean.getOrder_id());
                intent.putExtra("order_name", datasBean.getStore_name());
                context.startActivity(intent);


            }
        });

        if (datasBean.getOrder_state() == 0) {//已退货，已取消
            viewHolder.tvCanlePay.setVisibility(View.GONE);
            viewHolder.tvPay.setVisibility(View.VISIBLE);
            viewHolder.tvPay.setText("删除订单");
        } else if (datasBean.getOrder_state() == 20) {//待发货，待收货
            viewHolder.tvPay.setVisibility(View.GONE);
            viewHolder.tvCanlePay.setVisibility(View.VISIBLE);
            viewHolder.tv_dicus.setVisibility(View.GONE);
        } else if (datasBean.getOrder_state() == 40) {//已完成，待评价，删除订单
            viewHolder.tvPay.setText("删除订单");
            viewHolder.tvPay.setVisibility(View.VISIBLE);
            viewHolder.tvCanlePay.setVisibility(View.GONE);
            viewHolder.tv_dicus.setVisibility(View.VISIBLE);
        } else if (datasBean.getOrder_state() == 10) {//待付款
            viewHolder.tvPay.setVisibility(View.VISIBLE);
            viewHolder.tvCanlePay.setVisibility(View.VISIBLE);
            viewHolder.tv_dicus.setVisibility(View.GONE);
        }
        setItemEventClick(viewHolder);
//        viewHolder.orderPdItem.removeAllViews();
        //viewHolder.tvStatus.setText("代付款");
//        viewHolder.orderTime.setText(datas.getDatas().get(position).getAdd_time());
//        if(datas.getDatas().get(position).getOrder_amount().equals("0.00")) {
//            viewHolder.tvYhNum.setVisibility(View.GONE);
//        }else {
//            viewHolder.tvYhNum.setVisibility(View.VISIBLE);
//            viewHolder.tvYhNum.setText("优惠 - ¥ "+datas.getDatas().get(position).getOrder_amount());
//        }
//        viewHolder.tvTotalPrice.setText("¥ "+datas.getDatas().get(position).getOrder_amount());
//        if(datas.getDatas().get(position).getOrder_status().equals("0")&&datas.getDatas().get(position).getShipping_status().equals("0")
//                &&datas.getInfo().get(position).getPay_status().equals("0")) {
//            viewHolder.tvStatus.setText("代付款");
//            viewHolder.rPay.setVisibility(View.VISIBLE);
//            viewHolder.rObtain.setVisibility(View.GONE);
//            viewHolder.rEvaluate.setVisibility(View.GONE);
//        }else if(datas.getInfo().get(position).getOrder_status().equals("1")&&datas.getInfo().get(position).getShipping_status().equals("0")&&datas.getInfo().get(position).getPay_status().equals("2")) {
//            viewHolder.tvStatus.setText("代发货");
//            viewHolder.rPay.setVisibility(View.GONE);
//            viewHolder.rObtain.setVisibility(View.GONE);
//            viewHolder.rEvaluate.setVisibility(View.GONE);
//        }else if(datas.getInfo().get(position).getOrder_status().equals("1")&&datas.getInfo().get(position).getShipping_status().equals("1")&&datas.getInfo().get(position).getPay_status().equals("2")) {
//            viewHolder.tvStatus.setText("待收货");
//            viewHolder.rPay.setVisibility(View.GONE);
//            viewHolder.rObtain.setVisibility(View.VISIBLE);
//            viewHolder.rEvaluate.setVisibility(View.GONE);
//        }else if(datas.getInfo().get(position).getOrder_status().equals("1")&&datas.getInfo().get(position).getShipping_status().equals("2")&&datas.getInfo().get(position).getPay_status().equals("2")&&!datas.getInfo().get(position).getIs_evaluate().equals("1")) {
//            viewHolder.tvStatus.setText("待评价");
//            viewHolder.rPay.setVisibility(View.GONE);
//            viewHolder.rObtain.setVisibility(View.GONE);
//            viewHolder.rEvaluate.setVisibility(View.VISIBLE);
//        }else if(datas.getInfo().get(position).getOrder_status().equals("1")&&datas.getInfo().get(position).getShipping_status().equals("0")&&datas.getInfo().get(position).getPay_status().equals("2")&&datas.getInfo().get(position).getIs_evaluate().equals("1")) {
//            viewHolder.tvStatus.setText("交易完成");
//            viewHolder.rPay.setVisibility(View.GONE);
//            viewHolder.rObtain.setVisibility(View.GONE);
//            viewHolder.rEvaluate.setVisibility(View.GONE);
//        }

        /*for (int i = 0; i < datas.getInfo().get(position).getGoods_list().size(); i++) {
            View view = inflater.inflate(R.layout.item_order_pd_sub, null);
            ImageView pdImage = (ImageView) view.findViewById(R.id.iv_pd_img);
            TextView pdContent = (TextView) view.findViewById(R.id.tv_pd_content);
            TextView pdPrice = (TextView) view.findViewById(R.id.tv_pd_price);
            TextView pdNum = (TextView) view.findViewById(R.id.tv_pd_num);
            Glide.with(context).load(datas.getInfo().get(position).getGoods_list().get(i).getOriginal_img())
                    .placeholder(R.drawable.image_general)
                    .into(pdImage);
           *//*Glide.with(context).load(datas.getInfo().get(position).getGoods_list().get(i).getOriginal_img())
                    .placeholder(R.drawable.image_general)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)


                    .into(new GlideDrawableImageViewTarget(pdImage) {
                        @Override
                        public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                            super.onResourceReady(drawable, anim);
                        }
                    });*//*
            pdContent.setText(datas.getInfo().get(position).getGoods_list().get(i).getGoods_name());
            pdPrice.setText("¥ "+datas.getInfo().get(position).getGoods_list().get(i).getGoods_price());
            pdNum.setText("x "+datas.getInfo().get(position).getGoods_list().get(i).getGoods_number());

            viewHolder.orderPdItem.addView(view);
        }*/
    }

    public interface OnItemClickListener {
        void onCanlePayClick(View view, int postion, MyOrder.DatasBean bean);

        void onPayClick(View view, int postion, MyOrder.DatasBean bean);

        void onCommentClick(View view, int postion, MyOrder.DatasBean bean);

        void onItemLongClick(View view, int postion);
    }


    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    protected void setItemEventClick(final ViewHolder myViewHolder) {
        if (mOnItemClickListener != null) {
            myViewHolder.tvCanlePay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPostion = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onCanlePayClick(view, layoutPostion, datas.getDatas().get(layoutPostion));
                }
            });
            myViewHolder.tvPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPostion = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onPayClick(view, layoutPostion, datas.getDatas().get(layoutPostion));
                }
            });

            myViewHolder.tv_dicus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPostion = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onCommentClick(view, layoutPostion, datas.getDatas().get(layoutPostion));
                }
            });

            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int layoutPostion = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(view, layoutPostion);
                    return false;
                }
            });
        }
    }

    /**
     * 商品
     *
     * @param
     */
    /*public void addView(LinearLayout ll) {
        View view = inflater.inflate(R.layout.item_order_pd_sub, null);
        ll.addView(view);
    }*/
    @Override
    public int getItemCount() {
        if (null != datas) {
            return datas.getDatas().size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.order_number)
        TextView orderNumber;
        @Bind(R.id.order_status)
        TextView orderStatus;
        @Bind(R.id.riv_image)
        RoundAngleImageView rivImage;
        @Bind(R.id.tv_veget_name)
        TextView tvVegetName;
        @Bind(R.id.tv_veget_time)
        TextView tvVegetTime;
        @Bind(R.id.tv_total_money)
        TextView tvTotalMoney;
        @Bind(R.id.tv_pay)
        TextView tvPay;
        @Bind(R.id.tv_canle_pay)
        TextView tvCanlePay;

        @Bind(R.id.tv_dicus)
        TextView tv_dicus;

        @Bind(R.id.ll_look_details)
        LinearLayout ll_look_details;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.OrderDetails;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Samson on 2016/12/29.
 */

public class GoodsDetailsAdapter extends RecyclerView.Adapter {

    LayoutInflater inflater;
    Context context;
    List<OrderDetails.DatasBean.OrderGoodsBean> order_goods;

    public GoodsDetailsAdapter(Context context, List<OrderDetails.DatasBean.OrderGoodsBean> order_goods) {
        this.order_goods = order_goods;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order_list_details_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        OrderDetails.DatasBean.OrderGoodsBean orderGoodsBean = order_goods.get(position);
        Log.d("fadfd", "onBindViewHolder: "+orderGoodsBean.getGoods_name());
        viewHolder.tvName.setText(orderGoodsBean.getGoods_name());
        viewHolder.tvPrice.setText("￥"+orderGoodsBean.getGoods_price());
        viewHolder.tvNumber.setText("x"+orderGoodsBean.getGoods_num());
    }


    @Override
    public int getItemCount() {
        if (null != order_goods) {
            return order_goods.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_number)
        TextView tvNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

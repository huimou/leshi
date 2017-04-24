package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.SubmitSteOneBean;
import com.wangjia.yijiale.utils.StringFunction;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin
 * on 2015/7/2.
 */
public class ConfirmListViewAdapter extends RecyclerView.Adapter<ConfirmListViewAdapter.MyViewHolder> {
    private LayoutInflater minflater;
    private Context mContext;
    protected List<SubmitSteOneBean.DatasBean.StoreCartListBean.GoodsListBean> mDatas;
    private List<String> choses;

    public void setDatas(List<SubmitSteOneBean.DatasBean.StoreCartListBean.GoodsListBean> mDatas, int position) {
        this.mDatas = mDatas;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);

        void onItemLongClick(View view, int postion);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    public ConfirmListViewAdapter(Context context, List<SubmitSteOneBean.DatasBean.StoreCartListBean.GoodsListBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
        minflater = LayoutInflater.from(context);
        choses = new ArrayList<String>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = minflater.inflate(R.layout.activity_confirm_list_view_item, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        //myViewHolder.name.setText(mDatas.get(i));
        final int num = 0;
        SubmitSteOneBean.DatasBean.StoreCartListBean.GoodsListBean cartListBean = mDatas.get(i);
        if(cartListBean==null){
            return;
        }

        Glide.with(mContext).load(cartListBean.getGoods_image_url())
                .placeholder(R.drawable.image_general)
                .skipMemoryCache(true) //跳过内存缓存
                .into(myViewHolder.pic);
        myViewHolder.title.setText(cartListBean.getGoods_name());
        myViewHolder.price.setText("¥ " + cartListBean.getGoods_price());
        myViewHolder.buy_num_tv.setText("X "+String.valueOf(cartListBean.getGoods_num()));
        if(StringFunction.isNotNull(cartListBean.getGoods_spec())) {
            myViewHolder.guige_tv.setText(cartListBean.getGoods_spec());
        }else{
            myViewHolder.guige_tv.setText("[无规格]");
        }
        setItemEventClick(myViewHolder);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        } else {
            return 0;
        }
    }

    protected void setItemEventClick(final MyViewHolder myViewHolder) {
        if (mOnItemClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPostion = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(view, layoutPostion);
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.pic)
        ImageView pic;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.price)
        TextView price;
        @Bind(R.id.guige_tv)
        TextView guige_tv;
        @Bind(R.id.buy_num_tv)
        TextView buy_num_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.event.StatusBarEvent;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin
 * on 2015/7/2.
 */
public class CartListViewAdapter extends RecyclerView.Adapter<CartListViewAdapter.MyViewHolder> {

    private LayoutInflater minflater;
    private Context mContext;
    protected Cart mDatas;
    private List<String> choses;

    public void setDatas(Cart mDatas,int position) {
        this.mDatas = mDatas;
        if(position==-1){
            notifyDataSetChanged();
        }else{
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);

        void onItemLongClick(View view, int postion);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    public CartListViewAdapter(Context context, Cart datas) {
        this.mContext = context;
        this.mDatas = datas;
        minflater = LayoutInflater.from(context);
        choses = new ArrayList<String>();
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = minflater.inflate(R.layout.cart_list_view_item, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        L.i(""+i);
        myViewHolder.name.setText(mDatas.getDatas().getCart_list().get(i).getGoods_name());
        myViewHolder.price.setText(String.valueOf(Float.parseFloat(mDatas.getDatas().getCart_list().get(i).getGoods_price()) * Integer.parseInt(mDatas.getDatas().getCart_list().get(i).getGoods_sum())));
        myViewHolder.num.setText(mDatas.getDatas().getCart_list().get(i).getGoods_sum());
        myViewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RxBus.getDefault().send(new StatusBarEvent(""+i, "DetailActivity"));
                RxBus.getDefault().send(new StatusBarEvent("del", "ProductFragmentCart", i));
            }
        });

        myViewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RxBus.getDefault().send(new StatusBarEvent(""+i, "DetailActivity"));
                RxBus.getDefault().send(new StatusBarEvent("add", "ProductFragmentCart", i));
            }
        });
        L.i("" + i);
        setItemEventClick(myViewHolder);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.getDatas().getCart_list().size();
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
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.price)
        TextView price;
        @Bind(R.id.num)
        TextView num;
        @Bind(R.id.del)
        ImageView del;
        @Bind(R.id.add)
        ImageView add;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
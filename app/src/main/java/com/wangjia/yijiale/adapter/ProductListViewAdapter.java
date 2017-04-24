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
import com.wangjia.yijiale.entity.StoreGoodsList;
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
public class ProductListViewAdapter extends RecyclerView.Adapter<ProductListViewAdapter.MyViewHolder> {
    private LayoutInflater minflater;
    private Context mContext;
    protected StoreGoodsList mDatas;
    private List<String> choses;

    public void setDatas(StoreGoodsList mDatas, int position) {
        this.mDatas = mDatas;
//        notifyDataSetChanged();
        if (position == -1) {
            notifyDataSetChanged();
        } else {
            notifyItemChanged(position);
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

    public ProductListViewAdapter(Context context, StoreGoodsList datas) {
        this.mContext = context;
        this.mDatas = datas;
        minflater = LayoutInflater.from(context);
        choses = new ArrayList<String>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = minflater.inflate(R.layout.activity_product_list_view_item, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        //myViewHolder.name.setText(mDatas.get(i));
        final int num = 0;
        Glide.with(mContext).load(mDatas.getDatas().getGoods_list().get(i).getGoods_image_url())
                .placeholder(R.drawable.image_general)
                .skipMemoryCache(true) //跳过内存缓存
                .into(myViewHolder.pic);
        myViewHolder.title.setText(mDatas.getDatas().getGoods_list().get(i).getGoods_name());
        myViewHolder.price.setText("¥" + mDatas.getDatas().getGoods_list().get(i).getGoods_price());
        myViewHolder.num.setText(String.valueOf(mDatas.getDatas().getGoods_list().get(i).getCart_num()));
        myViewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myViewHolder.num.setText(String.valueOf(num-1));
                /*if(mDatas.getDatas().getGoods_list().get(i).getNum() == 0) {
                    L.TShort(mContext, "数量不能再减少了");
                }else {
                    mDatas.getDatas().getGoods_list().get(i).setNum(mDatas.getDatas().getGoods_list().get(i).getNum() - 1);
                    notifyDataSetChanged();
                }*/
                if (mDatas.getDatas().getGoods_list().get(i).getCart_num() != 0) {
                    RxBus.getDefault().send(new StatusBarEvent("del", "ProductFragmentList", i));
                } else {
                    L.TShort(mContext, "数量不能再减少了");
                }
            }
        });

        myViewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myViewHolder.num.setText(String.valueOf(num+1));
                /*mDatas.getDatas().getGoods_list().get(i).setNum(mDatas.getDatas().getGoods_list().get(i).getNum() + 1);
                notifyDataSetChanged();*/
                RxBus.getDefault().send(new StatusBarEvent("add", "ProductFragmentList", i));
            }
        });
        setItemEventClick(myViewHolder);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.getDatas().getGoods_list().size();
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
        @Bind(R.id.del)
        ImageView del;
        @Bind(R.id.num)
        TextView num;
        @Bind(R.id.add)
        ImageView add;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
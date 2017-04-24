package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjia.yijiale.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GuHaojiang
 * on 2015/7/2.
 */
public class PayRecordViewAdapter extends RecyclerView.Adapter<PayRecordViewAdapter.MyViewHolder> {

    private LayoutInflater minflater;
    private Context mContext;
    protected List<String> mDatas;
    private List<String> choses;
    private MyClickListener mListener;

    public void setDatas(List<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);

        void onItemLongClick(View view, int postion);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    public PayRecordViewAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        minflater = LayoutInflater.from(context);
        choses = new ArrayList<String>();
        this.mListener = mListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = minflater.inflate(R.layout.precord_view_item, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        myViewHolder.title.setText(mDatas.get(i));
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
        @Bind(R.id.insertTime)
        TextView insertTime;
        @Bind(R.id.orderstatu)
        TextView orderstatu;
        @Bind(R.id.delete)
        ImageView delete;
        @Bind(R.id.pic)
        ImageView pic;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.price)
        TextView price;
        @Bind(R.id.product_content)
        RelativeLayout product_content;
        @Bind(R.id.count_price)
        TextView countPrice;
        @Bind(R.id.num)
        TextView num;
        @Bind(R.id.state)
        TextView state;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 用于回掉的抽象类
     **/
    public static abstract class MyClickListener implements View.OnClickListener {
        /**
         * 基类的Onclick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick(((HashMap<String, Integer>) v.getTag()).get("position"), ((HashMap<String, Integer>) v.getTag()).get("type"), v);
        }

        public abstract void myOnClick(int position, int tag, View v);
    }

}
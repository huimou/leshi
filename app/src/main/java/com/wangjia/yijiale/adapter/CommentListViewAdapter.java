package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.CommentListBean;
import com.wangjia.yijiale.views.RoundAngleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin
 * on 2015/7/2.
 */
public class CommentListViewAdapter extends RecyclerView.Adapter<CommentListViewAdapter.MyViewHolder> {
    private LayoutInflater minflater;
    private Context mContext;
    protected List<CommentListBean.DatasBean.EvalListBean> mDatas;
    private List<String> choses;

    public void setDatas(List<CommentListBean.DatasBean.EvalListBean> mDatas, int position) {
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

    public CommentListViewAdapter(Context context, List<CommentListBean.DatasBean.EvalListBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
        minflater = LayoutInflater.from(context);
        choses = new ArrayList<String>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = minflater.inflate(R.layout.activity_comment_list_view_item, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        Glide.with(mContext).load(mDatas.get(i).getGeval_userimg())
                .placeholder(R.drawable.image_general)
                .skipMemoryCache(true) //跳过内存缓存
                .into(myViewHolder.pic);
        myViewHolder.name.setText(mDatas.get(i).getGeval_frommembername());
        myViewHolder.date_tv.setText(mDatas.get(i).getGeval_addtime());
        myViewHolder.content_tv.setText(mDatas.get(i).getGeval_content());

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
        RoundAngleImageView pic;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.date_tv)
        TextView date_tv;
        @Bind(R.id.content_tv)
        TextView content_tv;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
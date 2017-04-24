package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.StoreCollectionList;
import com.wangjia.yijiale.utils.HttpUtils;
import com.wangjia.yijiale.utils.Utils;
import com.wangjia.yijiale.views.SlidingButtonView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin
 * on 2015/7/2.
 */
public class StoreCollectionViewAdapter extends RecyclerView.Adapter<StoreCollectionViewAdapter.MyViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private LayoutInflater minflater;
    private Context mContext;
    protected StoreCollectionList mDatas;
    private List<String> choses;
    //private IonSlidingViewClickListener mIDeleteBtnClickListener;
    private SlidingButtonView mMenu = null;
    boolean allopen = false;
    private IonSlidingViewClickListener ionSlidingViewClickListener;

    public void setDatas(StoreCollectionList mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion , StoreCollectionList.DatasBean.FavoritesListBean bean);

        void onItemLongClick(View view, int postion);

        void onDeleteBtnCilck(View view, int postion, StoreCollectionList.DatasBean.FavoritesListBean bean);
    }



    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnIonSlidingViewClickListener(IonSlidingViewClickListener ionSlidingViewClickListener) {
        this.ionSlidingViewClickListener = ionSlidingViewClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    public StoreCollectionViewAdapter(Context context, StoreCollectionList datas) {
        this.mContext = context;
        this.mDatas = datas;
        minflater = LayoutInflater.from(context);
        choses = new ArrayList<String>();
        //mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = minflater.inflate(R.layout.collection_item, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        myViewHolder.slidingButtonView.setSlidingButtonListener(StoreCollectionViewAdapter.this);
        //设置内容布局的宽为屏幕宽度
        myViewHolder.layoutContent.getLayoutParams().width = Utils.getScreenWidth(mContext);
        myViewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int n = holder.getLayoutPosition();
                //mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });
        Glide.with(mContext).load(HttpUtils.getImageUrl(HttpUtils.getImageUrl(mDatas.getDatas().getFavorites_list().get(i).getStore_avatar_url())))
                .placeholder(R.drawable.image_general)
                .into(myViewHolder.ivPdImg);
        myViewHolder.tvPdContent.setText(mDatas.getDatas().getFavorites_list().get(i).getStore_name());
        myViewHolder.address.setText(mDatas.getDatas().getFavorites_list().get(i).getAddr());
        if (allopen) {
            myViewHolder.slidingButtonView.openMenu();
            myViewHolder.slidingButtonView.setCanTouch(false);
        } else {
            myViewHolder.slidingButtonView.closeMenu();
            myViewHolder.slidingButtonView.setCanTouch(true);
        }
        setItemEventClick(myViewHolder);
//        myViewHolder.layoutContent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int layoutPostion = myViewHolder.getLayoutPosition();
//                mOnItemClickListener.onItemClick(view, layoutPostion,mDatas.getDatas().getFavorites_list().get(layoutPostion));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.getDatas().getFavorites_list().size();
        } else {
            return 0;
        }
    }

    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    /**
     * 滑动或者点击了Item监听
     *
     * @param slidingButtonView
     */
    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }

    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position,StoreCollectionList.DatasBean.FavoritesListBean bean);

        void onDeleteBtnCilck(View view, int position);
    }

    public void addData(int position) {
        //mDatas.add(position, "添加项");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        //mDatas.remove(position);
        notifyItemRemoved(position);

    }

    protected void setItemEventClick(final MyViewHolder myViewHolder) {
        if (mOnItemClickListener != null) {
            myViewHolder.layoutContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPostion = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(view, layoutPostion,mDatas.getDatas().getFavorites_list().get(layoutPostion));
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

            myViewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPostion = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onDeleteBtnCilck(v, layoutPostion,mDatas.getDatas().getFavorites_list().get(layoutPostion));
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_delete)
        TextView tvDelete;
        @Bind(R.id.layout_content)
        LinearLayout layoutContent;
        @Bind(R.id.iv_pd_img)
        ImageView ivPdImg;
        @Bind(R.id.tv_pd_content)
        TextView tvPdContent;
        @Bind(R.id.address)
        TextView address;
        @Bind(R.id.all_sv)
        SlidingButtonView all_sv;
        public SlidingButtonView slidingButtonView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            slidingButtonView = (SlidingButtonView) itemView;
        }
    }

}
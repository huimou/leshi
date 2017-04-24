package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.AddressManageList;
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
public class AddressListViewAdapter extends RecyclerView.Adapter<AddressListViewAdapter.MyViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private LayoutInflater minflater;
    private Context mContext;
    protected AddressManageList mDatas;
    private List<String> choses;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;
    private SlidingButtonView mMenu = null;
    boolean allopen = false;

    public void setDatas(AddressManageList mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);

        void onItemLongClick(View view, int postion);

        void onDeleteBtnCilck(View view, int postion, AddressManageList.DatasBean.AddressListBean bean);

        void onUpdateBtnCilck(View view, int postion, AddressManageList.DatasBean.AddressListBean bean);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    public AddressListViewAdapter(Context context, AddressManageList datas) {
        this.mContext = context;
        this.mDatas = datas;
        minflater = LayoutInflater.from(context);
        choses = new ArrayList<String>();
//        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = minflater.inflate(R.layout.address_item, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        myViewHolder.slidingButtonView.setSlidingButtonListener(AddressListViewAdapter.this);
        //设置内容布局的宽为屏幕宽度
        myViewHolder.layoutContent.getLayoutParams().width = Utils.getScreenWidth(mContext);


        if("0".equals(mDatas.getDatas().getAddress_list().get(i).getIs_default())){
            myViewHolder.check_address_rb.setChecked(false);
        }else if("1".equals(mDatas.getDatas().getAddress_list().get(i).getIs_default())){
            myViewHolder.check_address_rb.setChecked(true);
        }

        myViewHolder.title.setText(mDatas.getDatas().getAddress_list().get(i).getTrue_name());
        myViewHolder.address.setText(mDatas.getDatas().getAddress_list().get(i).getProvince_name()+
                        mDatas.getDatas().getAddress_list().get(i).getCity_name()+
                        mDatas.getDatas().getAddress_list().get(i).getArea_name()+
                mDatas.getDatas().getAddress_list().get(i).getAddress());
        /*Glide.with(mContext).load(mDatas.getDatas().getFavorites_list().get(i).getGoods_image_url())
                .placeholder(R.drawable.image_general)
                .into(myViewHolder.ivPdImg);
        myViewHolder.tvPdContent.setText(String.valueOf(mDatas.getDatas().getFavorites_list().get(i).getGoods_name()));
        myViewHolder.address.setText(String.valueOf(mDatas.getDatas().getFavorites_list().get(i).getGoods_name()));*/
        if (allopen) {
            myViewHolder.slidingButtonView.openMenu();
            myViewHolder.slidingButtonView.setCanTouch(false);
        } else {
            myViewHolder.slidingButtonView.closeMenu();
            myViewHolder.slidingButtonView.setCanTouch(true);
        }
        setItemEventClick(myViewHolder);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.getDatas().getAddress_list().size();
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
        void onItemClick(View view, int position);

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


            myViewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onDeleteBtnCilck(v, n,mDatas.getDatas().getAddress_list().get(n));
                }
            });

            myViewHolder.update_address_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onUpdateBtnCilck(v, n,mDatas.getDatas().getAddress_list().get(n));
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_delete)
        TextView tvDelete;
        @Bind(R.id.layout_content)
        LinearLayout layoutContent;
        @Bind(R.id.check_address_rb)
        RadioButton check_address_rb;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.address)
        TextView address;
        @Bind(R.id.update_address_iv)
        ImageView update_address_iv;
        public SlidingButtonView slidingButtonView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            slidingButtonView = (SlidingButtonView) itemView;
        }
    }

}
package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.GoodsDetail;
import com.wangjia.yijiale.views.NoScrollGridView;


public class GoodsAttrListAdapter extends BaseAdapter implements GoodsGridViewAdapter.MyChildGridViewListener {

    private LayoutInflater layoutInflater;
    private GoodsDetail.DataBean list;
    // 上下文
    private Context context;

    //用来存放商品属性的ID
    private int[] json_AttrIds;

    OnParentOnChangeListener OnParentOnChangeListener;
    public GoodsAttrListAdapter(Context context, GoodsDetail.DataBean list, OnParentOnChangeListener OnParentOnChangeListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
        json_AttrIds=new int[list.getGoods_attr().size()];
        this.OnParentOnChangeListener=OnParentOnChangeListener;
       for(int i=0;i<list.getGoods_attr().size();i++){
           if(list.getGoods_attr().get(i).getValues().size()>0)
           json_AttrIds[i]=list.getGoods_attr().get(i).getValues().get(0).getSubid();
       }

    }

    public GoodsAttrListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 赋值
     *
     * @param list
     */
    public void setData(GoodsDetail.DataBean list) {
        this.list = list;
        // notifyDataSetChanged();
    }

    /**
     * 清除
     */
    public void clearData() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (list != null) {
            return list.getGoods_attr().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.goodsattr_extern_layout, null);
            holder.title = (TextView) convertView
                    .findViewById(R.id.title);
            holder.classify_listview = (NoScrollGridView) convertView
                    .findViewById(R.id.classify_listview);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.getGoods_attr().get(position).getName());
        holder.classify_listview.setAdapter(new GoodsGridViewAdapter(context, list.getGoods_attr().get(position),position,GoodsAttrListAdapter.this));

        return convertView;
    }


    //子GridView 每一次点击回调

    @Override
    public void OnClick(boolean isUpdate,int parentPos,int atts) {

        if(isUpdate){
            json_AttrIds[parentPos]=atts;
        }
        OnParentOnChangeListener.OnChange(json_AttrIds);
        for (int s:json_AttrIds)
        Log.e("Test","s:"+s);

    }

    private class ViewHolder {

        public TextView title;// 标题
        public NoScrollGridView classify_listview;//

    }


    /**
     * 父容器ListView 点击回调
     */
    public interface OnParentOnChangeListener{
        void OnChange(int[] atts);
    }
}
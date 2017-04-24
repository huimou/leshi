package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.GoodsDetail;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/6/2.
 */
public class GoodsGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private GoodsDetail.DataBean.GoodsAttrBean list;
    private MyChildGridViewListener myChildGridViewListener;
    private int parentPos; //父ListViewPosition

    private List<TextView> listTextView = new ArrayList<>();

    public GoodsGridViewAdapter(Context context, GoodsDetail.DataBean.GoodsAttrBean data, int parentPos, MyChildGridViewListener myChildGridViewListener) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.list = data;
        this.myChildGridViewListener = myChildGridViewListener;
        this.parentPos = parentPos;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.getValues().size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.getValues().get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        GridHolder holder = null;
        if (null == convertView) {
            holder = new GridHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.goods_item_extern_layout, null);
            holder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (GridHolder) convertView.getTag();
        }
        if (!listTextView.contains(holder.item_name))
            listTextView.add(holder.item_name);
        holder.item_name.setText(list.getValues().get(position).getLabel());
        if (position == 0)
            holder.item_name.setBackgroundResource(R.drawable.yuanjiao_choice);
        holder.item_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeChoise((TextView) v);
                myChildGridViewListener.OnClick(true, parentPos, list.getValues().get(position).getSubid());
            }
        });
        return convertView;
    }

    public static class GridHolder {
        TextView item_name;
    }

    //子Grid回调方法
    public interface MyChildGridViewListener {

        void OnClick(boolean isUpdate, int parentPos, int atts);
    }

    private void removeChoise(TextView textView) {

        for (TextView t : listTextView
                ) {
            if (t == textView) {
                t.setBackgroundResource(R.drawable.yuanjiao_choice);
            } else {
                t.setBackgroundResource(R.drawable.yuanjiao);
            }

        }
    }
}

package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.GoodsDetailInfo;

import java.util.List;


public class ChooesCatAdapter extends BaseAdapter {

    LayoutInflater inflater;
    Context context;
    private List<GoodsDetailInfo.DatasBean.GoodsInfoBean.SpecValueBean.SpecVBean> stringList;

    public ChooesCatAdapter(Context context, List<GoodsDetailInfo.DatasBean.GoodsInfoBean.SpecValueBean.SpecVBean> stringList) {
        this.context = context;
        this.stringList = stringList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stringList != null ? stringList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cat_item, null);
            holder.send_address_tv = (TextView) convertView.findViewById(R.id.send_address_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        GoodsDetailInfo.DatasBean.GoodsInfoBean.SpecValueBean.SpecVBean specVBean = stringList.get(position);
        holder.send_address_tv.setText(specVBean.getSpec_values());

        if (stringList.get(position).isSelect()) {
            holder.send_address_tv.setBackgroundResource(R.drawable.corners_has_check_bg);
        } else {
            holder.send_address_tv.setBackgroundResource(R.drawable.corners_no_check_bg);
        }

        return convertView;
    }

    public void chooceState(int position) {
        try {
			for (int i = 0;  i < stringList.size(); i++) {
                stringList.get(i).setSelect(false);
			}
            stringList.get(position).setSelect(true);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ViewHolder {
        TextView send_address_tv;
    }

}

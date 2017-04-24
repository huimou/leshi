package com.wangjia.yijiale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.GoodsDetailInfo;
import com.wangjia.yijiale.event.StatusBarEvent;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.views.ScrollGridView;

import java.util.List;

import static com.wangjia.yijiale.R.id.send_address_gv;


public class SendAddressAdapter extends BaseAdapter {

    LayoutInflater inflater;
    Context context;
    private List<GoodsDetailInfo.DatasBean.GoodsInfoBean.SpecValueBean> stringList;

    public SendAddressAdapter(Context context, List<GoodsDetailInfo.DatasBean.GoodsInfoBean.SpecValueBean> stringList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.ocoffee_send_address_item, null);
            holder.send_address_tv = (TextView) convertView.findViewById(R.id.send_address_name);
            holder.scrollGridView = (ScrollGridView) convertView.findViewById(send_address_gv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GoodsDetailInfo.DatasBean.GoodsInfoBean.SpecValueBean specValueBean = stringList.get(position);
        holder.send_address_tv.setText(specValueBean.getSpec_n());
        final ChooesCatAdapter adapter = new ChooesCatAdapter(context, specValueBean.getSpec_v());
        holder.scrollGridView.setAdapter(adapter);
        holder.scrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                adapter.chooceState(index);
                RxBus.getDefault().send(new StatusBarEvent(position+"", "choose_cat", specValueBean.getSpec_v().get(index).getSpec_id()));
            }
        });

        return convertView;
    }


    class ViewHolder {
        TextView send_address_tv;
        public ScrollGridView scrollGridView;
    }

}

package com.wangjia.yijiale.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.adapter.FragmentTabAdapter;
import com.wangjia.yijiale.otherfragment.OrderAllFragment;
import com.wangjia.yijiale.otherfragment.OrderHasNoPayFragment;
import com.wangjia.yijiale.otherfragment.OrderSalesReturnFragment;
import com.wangjia.yijiale.otherfragment.OrderWaitEvaluteFragment;
import com.wangjia.yijiale.otherfragment.OrderWaitPlayFragment;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.Titlebulder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的订单 主页
 */
public class MyOrderActivity extends AppCompatActivity {

    @Bind(R.id.order_top_tabs)
    RadioGroup orderTopTabs;
    /**
     * Called when the activity is first created.
     */
    public List<Fragment> fragments;
    FragmentTabAdapter tabAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        new Titlebulder(this).setTitleName("我的订单")
                .setLeftImage(R.mipmap.arrowleft)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        fragments = new ArrayList<Fragment>();
        fragments.add(new OrderAllFragment());//全部订单
        fragments.add(new OrderHasNoPayFragment());//待付款
        fragments.add(new OrderWaitPlayFragment());//待发货
        fragments.add(new OrderWaitEvaluteFragment());//待收货
        fragments.add(new OrderSalesReturnFragment());//已完成
        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.order_content, orderTopTabs, 0);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                L.e("Extra---- " + index + " checked!!! ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

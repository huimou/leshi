package com.wangjia.yijiale.iview;


import com.wangjia.yijiale.entity.MyOrder;

/**
 * Created by kevin on 2016/10/26
 */
public interface MyOrderActivityView extends BaseView {
    void getData(MyOrder model);

    void orderOperte(MyOrder getInfo);
}

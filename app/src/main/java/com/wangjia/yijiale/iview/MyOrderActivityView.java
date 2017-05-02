package com.wangjia.yijiale.iview;


import com.wangjia.yijiale.entity.MyOrder;
import com.wangjia.yijiale.entity.SubmitOrderBean;

/**
 * Created by kevin on 2016/10/26
 */
public interface MyOrderActivityView extends BaseView {
    void getData(MyOrder model);

    void orderOperte(MyOrder getInfo);

    void orderSubmitPlay(SubmitOrderBean getInfo);
}

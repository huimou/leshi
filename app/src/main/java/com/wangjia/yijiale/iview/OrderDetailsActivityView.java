package com.wangjia.yijiale.iview;


import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.OrderDetails;

/**
 * Created by kevin on 2016/10/26
 */
public interface OrderDetailsActivityView extends BaseView {
    void getData(OrderDetails model);

    void commentOrder(BaseBean getInfo);
}

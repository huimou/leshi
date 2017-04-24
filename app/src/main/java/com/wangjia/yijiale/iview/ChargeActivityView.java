package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.entity.VipSubmitBean;
import com.wangjia.yijiale.entity.ZhifuShiWuBean;

/**
 * Created by kevin on 2016/10/26
 */
public interface ChargeActivityView extends BaseView {

    void showVip(ShowVipBean getInfo);

    void orderSubmitPlay(SubmitOrderBean getInfo);

    void vipSubmitOrder(VipSubmitBean getInfo);

    void shiwuOrder(ZhifuShiWuBean getInfo);
}

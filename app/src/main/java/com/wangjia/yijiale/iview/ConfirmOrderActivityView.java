package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.AddressManageList;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.entity.SubmitSteOneBean;
import com.wangjia.yijiale.entity.SubmitSteTwoBean;

/**
 * Created by kevin on 2016/10/26
 */
public interface ConfirmOrderActivityView extends BaseView {

    void getData(Cart model);


    void getDefaultAddress(AddressManageList getInfo);

    void submitOrderForOnce(SubmitSteOneBean getInfo);

    void submitOrderForTwo(SubmitSteTwoBean getInfo);
}

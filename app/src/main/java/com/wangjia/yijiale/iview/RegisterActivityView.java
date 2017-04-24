package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.AboutBean;
import com.wangjia.yijiale.entity.CodeBean;
import com.wangjia.yijiale.entity.Register;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.entity.ZhifuShiWuBean;

/**
 * Created by kevin on 2016/10/26
 */
public interface RegisterActivityView extends BaseView {
    void getData(Register model);

    /**
     * 获取验证码
     * @param model
     */
    void getCode(CodeBean model);

    void getAbout(AboutBean getInfo);

    void showVip(ShowVipBean getInfo);

    void shiwuOrder(ZhifuShiWuBean getInfo);

    void orderSubmitPlay(SubmitOrderBean getInfo);
}

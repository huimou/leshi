package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface RegisterActivityPresenter extends BasePresenter {
    void getData(String member_mobile, String member_password,String verific);

    /**
     * 获取验证码
     * @param mobile
     * @param type   1表示注册，2表示找回密码
     */
    void getCode(String mobile, String type);

    void getAbout();

    void showVip();

    void shiwuOrder(String ply_sn);

    void orderSubmitPlay(String ply_sn,String payment_method);
}

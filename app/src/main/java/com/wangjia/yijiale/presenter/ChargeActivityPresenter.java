package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface ChargeActivityPresenter extends BasePresenter {

    void showVip();

    void orderSubmitPlay(String ply_sn, String payment_method);

    void vipSubmitOrder(String token, String pdr_amount, String payment_method);

    void shiwuOrder(String ply_sn);
}

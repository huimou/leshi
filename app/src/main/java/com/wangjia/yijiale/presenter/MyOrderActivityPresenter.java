package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface MyOrderActivityPresenter extends BasePresenter {
    void getData (String token, int status,int page_now);
    void orderOperte(String token, String order_id, String state_type);
    void orderSubmitPlay(String ply_sn,String payment_method,String pd_pay);
}

package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface QrCodeActivityPresenter extends BasePresenter {
    void getData(int store_id) ;
    void  payMoneyForStore(String token,String amount,int store_id) ;

}

package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface ConfirmOrderActivityPresenter extends BasePresenter {

    void getData(String token, String store_id);

    void getDefaultAddress( String token);

    void submitOrderForOnce(String token,String store_id,int ifcart,String cart_id,String address_id);

    void submitOrderForTwo(String token, String goods_id, int ifcart, String cart_id, String address_id, String pay_message,
                           String pay_name, String vat_hash,
                           String allow_offpay, String offpay_hash,
                           String offpay_hash_batch, String buy_city_id);

}

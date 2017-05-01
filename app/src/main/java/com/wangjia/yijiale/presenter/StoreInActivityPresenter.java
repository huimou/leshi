package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface StoreInActivityPresenter extends BasePresenter {
    void getData(String token, String company_name, String company_address_detail, String contacts_name, String contacts_phone);
}

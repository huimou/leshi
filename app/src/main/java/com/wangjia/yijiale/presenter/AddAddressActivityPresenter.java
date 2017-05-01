package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface AddAddressActivityPresenter extends BasePresenter {
    void getData(String token, String true_name, String city_id, String area_id, String tel_phone,
                 String is_default, String post_code, String address, String province_id, String area_info);
    void getUpdateAddress(String token, String address_id,String true_name, String city_id, String area_id, String tel_phone,
                 String is_default, String post_code, String address, String province_id,String area_info);
}

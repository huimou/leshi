package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface StoreInActivityPresenter extends BasePresenter {
    void getData(String token, String true_name, String city_id, String area_id, String tel_phone,
                 String is_default, String post_code, String address, String province_id, String 手持身份证照片);
}

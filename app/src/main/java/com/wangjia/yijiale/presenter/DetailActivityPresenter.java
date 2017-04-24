package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface DetailActivityPresenter extends BasePresenter {
    void getData(String token, String store_id);
    void changeCartNum(String cart_id, String quantity, String token);
    void getStoreData(String store_id);
    void setCollect(String store_id,int is_favorate);
    void clearShopping(String store_id, String token);
}

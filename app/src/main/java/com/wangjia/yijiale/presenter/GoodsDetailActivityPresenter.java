package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface GoodsDetailActivityPresenter extends BasePresenter {
    void getGoodsData(String token, String goods_id);
    void setGoodsCollect(String goods_id, int is_favorate);
    void getData(String token, String store_id);

    void changeCartNum(String cart_id, String quantity, String token);

    void changeNum(String goods_id, String quantity, String token);

    void clearShopping(String store_id, String token);
}

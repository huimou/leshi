package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface MyCollectionActivityPresenter extends BasePresenter {
    void getCollectionList(String token, String now_page);

    void setGoodsCollect(String goods_id);
}

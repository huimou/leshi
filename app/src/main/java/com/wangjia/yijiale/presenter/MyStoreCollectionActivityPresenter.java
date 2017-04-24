package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface MyStoreCollectionActivityPresenter extends BasePresenter {
    void getStoreCollectionList(String token, String now_page);
    void setCollect(String store_id);
}

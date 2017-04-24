package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface ProductListFragmentPresenter extends BasePresenter {
    void getData(String store_id, String now_page);
    void changeNum(String goods_id, String quantity, String token);
    void getStoreComment(int store_id, int now_page,int now_sum);

}

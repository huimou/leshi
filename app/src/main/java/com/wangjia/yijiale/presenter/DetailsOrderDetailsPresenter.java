package com.wangjia.yijiale.presenter;


/**
 * Created by wadel on 2017/4/22.
 * 订单详情页面的
 */

public interface DetailsOrderDetailsPresenter extends BasePresenter {

    void getData(String token, int order_id);
    void get_member_evaluate(String token, int order_id);
    void commentOrder(String token, int order_id, int store_desccredit, int store_servicecredit, int store_deliverycredit, int goods);

    }

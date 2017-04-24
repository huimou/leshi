package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface TxActivityPresenter extends BasePresenter {

    void showVip();

    void vipTxApply(String token, String pdc_amount, String pdc_bank_name, String pdc_bank_no,
               String pdc_bank_user, String pdc_bank_province, String pdc_bank_city);

}

package com.wangjia.yijiale.entity;

/**
 * Created by kevin on 2016/10/26
 */

public class SubmitSteTwoBean {


    /**
     * code : 200
     * msg : 获取支付单号成功
     * datas : {"pay_sn":"830543678962456013","payment_code":"online"}
     */

    private int code;
    private String msg;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * pay_sn : 830543678962456013
         * payment_code : online
         */

        private String pay_sn;
        private String payment_code;

        public String getPay_sn() {
            return pay_sn;
        }

        public void setPay_sn(String pay_sn) {
            this.pay_sn = pay_sn;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }
    }
}

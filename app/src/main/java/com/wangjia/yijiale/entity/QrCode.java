package com.wangjia.yijiale.entity;

/**
 * Created by kevin on 2016/10/26
 */

public class QrCode {


    /**
     * code : 200
     * msg : 二维码获取成功
     * datas : {"store_name":"好商城V5","code_url":"http://cs.j.cqxueao.cn/mobileapp/cashier/code_1.png"}
     */

    private String code;
    private String msg;
    private DatasBean datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * store_name : 好商城V5
         * code_url : http://cs.j.cqxueao.cn/mobileapp/cashier/code_1.png
         */

        private String store_name;
        private String code_url;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getCode_url() {
            return code_url;
        }

        public void setCode_url(String code_url) {
            this.code_url = code_url;
        }
    }
}

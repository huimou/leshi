package com.wangjia.yijiale.entity;

/**
 * Created by kevin on 2016/10/26
 */

public class MyStoreDetail {


    /**
     * code : 200
     * msg : 商家详情！
     * datas : {"addr":"","store_phone":"18723151485","store_zy":"专业女装，专业女包，品质有保障"}
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
         * addr :
         * store_phone : 18723151485
         * store_zy : 专业女装，专业女包，品质有保障
         */

        private String addr;
        private String store_phone;
        private String store_zy;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getStore_phone() {
            return store_phone;
        }

        public void setStore_phone(String store_phone) {
            this.store_phone = store_phone;
        }

        public String getStore_zy() {
            return store_zy;
        }

        public void setStore_zy(String store_zy) {
            this.store_zy = store_zy;
        }
    }
}

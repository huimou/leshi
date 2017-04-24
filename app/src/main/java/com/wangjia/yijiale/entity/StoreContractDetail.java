package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

/**
 * Created by kevin on 2016/10/26
 */

public class StoreContractDetail {

    /**
     * code : 200
     * datas : {"addr":"","store_phone":null,"store_zy":null}
     */

    private int code;
    /**
     * addr :
     * store_phone : null
     * store_zy : null
     */

    private DatasBean datas;

    public static StoreContractDetail objectFromData(String str) {

        return new Gson().fromJson(str, StoreContractDetail.class);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String addr;
        private Object store_phone;
        private Object store_zy;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public Object getStore_phone() {
            return store_phone;
        }

        public void setStore_phone(Object store_phone) {
            this.store_phone = store_phone;
        }

        public Object getStore_zy() {
            return store_zy;
        }

        public void setStore_zy(Object store_zy) {
            this.store_zy = store_zy;
        }
    }
}

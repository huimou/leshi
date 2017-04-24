package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class AddAddress {

    /**
     * code : 200
     * datas : {"address_list":[]}
     */

    private int code;
    private DatasBean datas;

    public static AddAddress objectFromData(String str) {

        return new Gson().fromJson(str, AddAddress.class);
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
        private List<?> address_list;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public List<?> getAddress_list() {
            return address_list;
        }

        public void setAddress_list(List<?> address_list) {
            this.address_list = address_list;
        }
    }
}

package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class StoreList {

    /**
     * code : 200
     * hasmore : false
     * page_total : 1
     * datas : {"store_list":[{"store_id":"1","store_name":"好商城V5","addr":"","store_avatar_url":"http://192.168.0.10:182/data/upload/shop/common/default_store_avatar.png"},{"store_id":"2","store_name":"花花公子","addr":"","store_avatar_url":"http://192.168.0.10:182/data/upload/shop/store/05354582644316773_sm.png"},{"store_id":"3","store_name":"我的店铺1","addr":"天津天津市和平区13527409446","store_avatar_url":"http://192.168.0.10:182/data/upload/shop/common/default_store_avatar.png"}]}
     */

    private int code;
    private boolean hasmore;
    private int page_total;
    private DatasBean datas;

    public static StoreList objectFromData(String str) {

        return new Gson().fromJson(str, StoreList.class);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * store_id : 1
         * store_name : 好商城V5
         * addr :
         * store_avatar_url : http://192.168.0.10:182/data/upload/shop/common/default_store_avatar.png
         */

        private List<StoreListBean> store_list;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public List<StoreListBean> getStore_list() {
            return store_list;
        }

        public void setStore_list(List<StoreListBean> store_list) {
            this.store_list = store_list;
        }

        public static class StoreListBean {
            private int store_id;
            private String store_name;
            private String addr;
            private String store_avatar_url;

            public static StoreListBean objectFromData(String str) {

                return new Gson().fromJson(str, StoreListBean.class);
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getStore_avatar_url() {
                return store_avatar_url;
            }

            public void setStore_avatar_url(String store_avatar_url) {
                this.store_avatar_url = store_avatar_url;
            }
        }
    }
}

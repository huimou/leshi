package com.wangjia.yijiale.entity;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class SearchShopBean {


    /**
     * code : 200
     * msg : 获得店铺成功
     * datas : {"store_list":[{"store_id":"2","store_name":"花花公子","addr":"重庆大坪广场","store_avatar_url":"http://localhost/data/upload/shop/store/05354582644316773_sm.png"},{"store_id":"3","store_name":"我的店铺1","addr":"天津天津市和平区13527409446","store_avatar_url":"http://localhost/data/upload/shop/common/default_store_avatar.png"}]}
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
        private List<StoreListBean> store_list;

        public List<StoreListBean> getStore_list() {
            return store_list;
        }

        public void setStore_list(List<StoreListBean> store_list) {
            this.store_list = store_list;
        }

        public static class StoreListBean {
            /**
             * store_id : 2
             * store_name : 花花公子
             * addr : 重庆大坪广场
             * store_avatar_url : http://localhost/data/upload/shop/store/05354582644316773_sm.png
             */

            private int store_id;
            private String store_name;
            private String addr;
            private String store_avatar_url;

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

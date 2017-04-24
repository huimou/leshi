package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class StoreCollectionList {

    /**
     * code : 200
     * now_page : 1
     * total_page : 1
     * datas : {"favorites_list":[{"goods_id":"100008","goods_name":"劳力士Rolex 宇宙计型迪通拿 自动机械皮带男表 正品116519 CR.TB","goods_image":"1_04752627931531971.jpg","store_id":"1","fav_id":"100008","goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627931531971_240.jpg","goods_price":"188550.00"}]}
     */

    private int code;
    private int now_page;
    private int total_page;
    private DatasBean datas;

    public static StoreCollectionList objectFromData(String str) {

        return new Gson().fromJson(str, StoreCollectionList.class);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getNow_page() {
        return now_page;
    }

    public void setNow_page(int now_page) {
        this.now_page = now_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {

        private List<FavoritesListBean> favorites_list;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public List<FavoritesListBean> getFavorites_list() {
            return favorites_list;
        }

        public void setFavorites_list(List<FavoritesListBean> favorites_list) {
            this.favorites_list = favorites_list;
        }

        public static class FavoritesListBean {

            /**
             * store_id : 1
             * store_name : 好商城V5
             * addr :
             * store_avatar_url : http://localhost/data/upload/shop/common/default_store_avatar.png
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

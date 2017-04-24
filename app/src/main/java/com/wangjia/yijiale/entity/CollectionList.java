package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class CollectionList {

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

    public static CollectionList objectFromData(String str) {

        return new Gson().fromJson(str, CollectionList.class);
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
        /**
         * goods_id : 100008
         * goods_name : 劳力士Rolex 宇宙计型迪通拿 自动机械皮带男表 正品116519 CR.TB
         * goods_image : 1_04752627931531971.jpg
         * store_id : 1
         * fav_id : 100008
         * goods_image_url : http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627931531971_240.jpg
         * goods_price : 188550.00
         */

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
            private String goods_id;
            private String goods_name;
            private String goods_image;
            private String store_id;
            private String fav_id;
            private String goods_image_url;
            private String goods_price;

            public static FavoritesListBean objectFromData(String str) {

                return new Gson().fromJson(str, FavoritesListBean.class);
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getFav_id() {
                return fav_id;
            }

            public void setFav_id(String fav_id) {
                this.fav_id = fav_id;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }
        }
    }
}

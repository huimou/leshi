package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class StoreGoodsList {

    /**
     * code : 200
     * datas : {"goods_list":[{"goods_id":"100003","store_id":"1","goods_name":"劳力士Rolex 日志型系列 自动机械钢带男士表 联保正品 116333","goods_price":"89200.00","goods_marketprice":"89200.00","goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627769865296_360.jpg","num ":0},{"goods_id":"100009","store_id":"1","goods_name":"劳力士Rolex 日志型系列 116200 63200 自动机械钢带男表联保正品","goods_price":"42800.00","goods_marketprice":"52800.00","goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627958339099_360.jpg","num ":0},{"goods_id":"100001","store_id":"1","goods_name":"劳力士Rolex 深海系列 自动机械钢带男士表 联保正品116660 98210","goods_price":"87500.00","goods_marketprice":"87500.00","goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627707766698_360.png","num ":0},{"goods_id":"100007","store_id":"1","goods_name":"劳力士Rolex 蚝式恒动系列自动机械钢带男表正品116523-8DI-78593","goods_price":"146300.00","goods_marketprice":"146300.00","goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627900055146_360.png","num ":0},{"goods_id":"100006","store_id":"1","goods_name":"劳力士Rolex 蚝式恒动系列 自动机械钢带男表 正品116231-G-63201","goods_price":"100500.00","goods_marketprice":"100500.00","goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627871532105_360.png","num ":0}]}
     */

    private int code;
    private DatasBean datas;

    public static StoreGoodsList objectFromData(String str) {

        return new Gson().fromJson(str, StoreGoodsList.class);
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
        /**
         * goods_id : 100003
         * store_id : 1
         * goods_name : 劳力士Rolex 日志型系列 自动机械钢带男士表 联保正品 116333
         * goods_price : 89200.00
         * goods_marketprice : 89200.00
         * goods_image_url : http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627769865296_360.jpg
         * num  : 0
         */

        private List<GoodsListBean> goods_list;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            private String goods_id;
            private String store_id;
            private String goods_name;
            private String goods_price;
            private String goods_marketprice;
            private String goods_image_url;
            private int cart_num;

            public static GoodsListBean objectFromData(String str) {

                return new Gson().fromJson(str, GoodsListBean.class);
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_marketprice() {
                return goods_marketprice;
            }

            public void setGoods_marketprice(String goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }

            public int getCart_num() {
                return cart_num;
            }

            public void setCart_num(int cart_num) {
                this.cart_num = cart_num;
            }
        }
    }
}

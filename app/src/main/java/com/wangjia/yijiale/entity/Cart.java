package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class Cart {

    /**
     * code : 200
     * datas : {"cart_list":[{"cart_id":"2","goods_name":"劳力士Rolex 日志型系列 自动机械钢带男士表 联保正品 116333","goods_id":"100003","goods_price":"89200.00","goods_sum":"2","goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627769865296_240.jpg"}],"sum_price":"178400.00","sum":1}
     */

    private int code;
    /**
     * cart_list : [{"cart_id":"2","goods_name":"劳力士Rolex 日志型系列 自动机械钢带男士表 联保正品 116333","goods_id":"100003","goods_price":"89200.00","goods_sum":"2","goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627769865296_240.jpg"}]
     * sum_price : 178400.00
     * sum : 1
     */

    private DatasBean datas;

    public static Cart objectFromData(String str) {

        return new Gson().fromJson(str, Cart.class);
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
        private String sum_price;
        private int sum;
        /**
         * cart_id : 2
         * goods_name : 劳力士Rolex 日志型系列 自动机械钢带男士表 联保正品 116333
         * goods_id : 100003
         * goods_price : 89200.00
         * goods_sum : 2
         * goods_image_url : http://192.168.0.10:182/data/upload/shop/store/goods/1/1_04752627769865296_240.jpg
         */

        private List<CartListBean> cart_list;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public String getSum_price() {
            return sum_price;
        }

        public void setSum_price(String sum_price) {
            this.sum_price = sum_price;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public List<CartListBean> getCart_list() {
            return cart_list;
        }

        public void setCart_list(List<CartListBean> cart_list) {
            this.cart_list = cart_list;
        }

        public static class CartListBean {
            private String cart_id;
            private String goods_name;
            private String goods_id;
            private String goods_price;
            private String goods_sum;
            private String goods_image_url;

            public static CartListBean objectFromData(String str) {

                return new Gson().fromJson(str, CartListBean.class);
            }

            public String getCart_id() {
                return cart_id;
            }

            public void setCart_id(String cart_id) {
                this.cart_id = cart_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_sum() {
                return goods_sum;
            }

            public void setGoods_sum(String goods_sum) {
                this.goods_sum = goods_sum;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }
        }
    }
}

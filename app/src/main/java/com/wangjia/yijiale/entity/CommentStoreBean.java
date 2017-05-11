package com.wangjia.yijiale.entity;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class CommentStoreBean {


    /**
     * code : 200
     * msg : 数据获取成功
     * datas : {"store":{"store_id":"5","store_name":"杨树的测试店铺","is_own_shop":"0"},"order_goods":[{"rec_id":"226","order_id":"194","goods_id":"100049","goods_name":"ceshi 白色 大","goods_price":"10.00","goods_num":"1","goods_image":"5_05459125064250463.jpg","goods_pay_price":"10.00","store_id":"5","buyer_id":"10","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"162","goods_spec":"颜色：白色，尺寸：大","goods_contractid":"","invite_rates":"0","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05459125064250463_240.jpg"},{"rec_id":"225","order_id":"194","goods_id":"100034","goods_name":"欧美菜篮子编织女包羊皮妈咪袋女包真皮大包手提包大容量单肩包包 白色 大","goods_price":"699.00","goods_num":"1","goods_image":"5_05451377054730931.jpg","goods_pay_price":"699.00","store_id":"5","buyer_id":"10","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"162","goods_spec":"颜色：白色，尺寸：大","goods_contractid":"","invite_rates":"0","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377054730931_240.jpg"}]}
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
         * store : {"store_id":"5","store_name":"杨树的测试店铺","is_own_shop":"0"}
         * order_goods : [{"rec_id":"226","order_id":"194","goods_id":"100049","goods_name":"ceshi 白色 大","goods_price":"10.00","goods_num":"1","goods_image":"5_05459125064250463.jpg","goods_pay_price":"10.00","store_id":"5","buyer_id":"10","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"162","goods_spec":"颜色：白色，尺寸：大","goods_contractid":"","invite_rates":"0","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05459125064250463_240.jpg"},{"rec_id":"225","order_id":"194","goods_id":"100034","goods_name":"欧美菜篮子编织女包羊皮妈咪袋女包真皮大包手提包大容量单肩包包 白色 大","goods_price":"699.00","goods_num":"1","goods_image":"5_05451377054730931.jpg","goods_pay_price":"699.00","store_id":"5","buyer_id":"10","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"162","goods_spec":"颜色：白色，尺寸：大","goods_contractid":"","invite_rates":"0","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377054730931_240.jpg"}]
         */

        private StoreBean store;
        private List<OrderGoodsBean> order_goods;

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public List<OrderGoodsBean> getOrder_goods() {
            return order_goods;
        }

        public void setOrder_goods(List<OrderGoodsBean> order_goods) {
            this.order_goods = order_goods;
        }

        public static class StoreBean {
            /**
             * store_id : 5
             * store_name : 杨树的测试店铺
             * is_own_shop : 0
             */

            private int store_id;
            private String store_name;
            private int is_own_shop;

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

            public int getIs_own_shop() {
                return is_own_shop;
            }

            public void setIs_own_shop(int is_own_shop) {
                this.is_own_shop = is_own_shop;
            }
        }

        public static class OrderGoodsBean {
            /**
             * rec_id : 226
             * order_id : 194
             * goods_id : 100049
             * goods_name : ceshi 白色 大
             * goods_price : 10.00
             * goods_num : 1
             * goods_image : 5_05459125064250463.jpg
             * goods_pay_price : 10.00
             * store_id : 5
             * buyer_id : 10
             * goods_type : 1
             * promotions_id : 0
             * commis_rate : 200
             * gc_id : 162
             * goods_spec : 颜色：白色，尺寸：大
             * goods_contractid :
             * invite_rates : 0
             * goods_image_url : http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05459125064250463_240.jpg
             */

            private int rec_id;
            private int order_id;
            private String goods_id;
            private String goods_name;
            private double goods_price;
            private int goods_num;
            private String goods_image;
            private double goods_pay_price;
            private int store_id;
            private int buyer_id;
            private int goods_type;
            private String promotions_id;
            private String commis_rate;
            private String gc_id;
            private String goods_spec;
            private String goods_contractid;
            private String invite_rates;
            private String goods_image_url;

            public int getRec_id() {
                return rec_id;
            }

            public void setRec_id(int rec_id) {
                this.rec_id = rec_id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
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

            public double getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(double goods_price) {
                this.goods_price = goods_price;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public double getGoods_pay_price() {
                return goods_pay_price;
            }

            public void setGoods_pay_price(double goods_pay_price) {
                this.goods_pay_price = goods_pay_price;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(int buyer_id) {
                this.buyer_id = buyer_id;
            }

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public String getPromotions_id() {
                return promotions_id;
            }

            public void setPromotions_id(String promotions_id) {
                this.promotions_id = promotions_id;
            }

            public String getCommis_rate() {
                return commis_rate;
            }

            public void setCommis_rate(String commis_rate) {
                this.commis_rate = commis_rate;
            }

            public String getGc_id() {
                return gc_id;
            }

            public void setGc_id(String gc_id) {
                this.gc_id = gc_id;
            }

            public String getGoods_spec() {
                return goods_spec;
            }

            public void setGoods_spec(String goods_spec) {
                this.goods_spec = goods_spec;
            }

            public String getGoods_contractid() {
                return goods_contractid;
            }

            public void setGoods_contractid(String goods_contractid) {
                this.goods_contractid = goods_contractid;
            }

            public String getInvite_rates() {
                return invite_rates;
            }

            public void setInvite_rates(String invite_rates) {
                this.invite_rates = invite_rates;
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

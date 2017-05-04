package com.wangjia.yijiale.entity;

import java.util.List;

/**
 * Created by wadel on 2017/4/22.
 */

public class OrderDetails {

    /**
     * code : 200
     * msg : 订单信息获取成功
     * datas : {"store_avatar":"http://cs.j.cqxueao.cn/data/upload/shop//store/05448792822646983_sm.jpg","area_info":"重庆 重庆市 渝北区","store_address":"重庆市渝北区义学路32号","order_sn":"9000000000001801","add_time":"2017-04-11 10:32","payment_code":"predeposit","order_amount":"699.00","order_id":"6","deliver_explain":null,"payment_code_text":"站内余额支付","order_goods":[{"goods_name":"欧美菜篮子编织女包羊皮妈咪袋女包真皮大包手提包大容量单肩包包 彩兰 大","goods_price":"699.00","goods_num":"1"}]}
     */

    private String code;
    private String msg;
    private DatasBean datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * store_avatar : http://cs.j.cqxueao.cn/data/upload/shop//store/05448792822646983_sm.jpg
         * area_info : 重庆 重庆市 渝北区
         * store_address : 重庆市渝北区义学路32号
         * order_sn : 9000000000001801
         * add_time : 2017-04-11 10:32
         * payment_code : predeposit
         * order_amount : 699.00
         * order_id : 6
         * deliver_explain : null
         * payment_code_text : 站内余额支付
         * order_goods : [{"goods_name":"欧美菜篮子编织女包羊皮妈咪袋女包真皮大包手提包大容量单肩包包 彩兰 大","goods_price":"699.00","goods_num":"1"}]
         */

        private String store_avatar;
        private String area_info;
        private String store_address;
        private String store_name;
        private String order_sn;
        private String add_time;
        private String payment_code;
        private String order_amount;
        private String order_id;
        private String deliver_explain;
        private String payment_code_text;
        private List<OrderGoodsBean> order_goods;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_avatar() {
            return store_avatar;
        }

        public void setStore_avatar(String store_avatar) {
            this.store_avatar = store_avatar;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getDeliver_explain() {
            return deliver_explain;
        }

        public void setDeliver_explain(String deliver_explain) {
            this.deliver_explain = deliver_explain;
        }

        public String getPayment_code_text() {
            return payment_code_text;
        }

        public void setPayment_code_text(String payment_code_text) {
            this.payment_code_text = payment_code_text;
        }

        public List<OrderGoodsBean> getOrder_goods() {
            return order_goods;
        }

        public void setOrder_goods(List<OrderGoodsBean> order_goods) {
            this.order_goods = order_goods;
        }

        public static class OrderGoodsBean {
            /**
             * goods_name : 欧美菜篮子编织女包羊皮妈咪袋女包真皮大包手提包大容量单肩包包 彩兰 大
             * goods_price : 699.00
             * goods_num : 1
             */

            private String goods_name;
            private String goods_price;
            private String goods_num;

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

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }
        }
    }
}

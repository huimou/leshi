package com.wangjia.yijiale.entity;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class MyOrder {


    /**
     * code : 200
     * msg : 订单列表获取成功！
     * datas : [{"order_sn":"9000000000001801","order_state":"20","order_state_text":"待发货","store_avatar":"http://cs.j.cqxueao.cn/data/upload/shop//store/05448792822646983_sm.jpg","store_name":"杨树的测试店铺","add_time":"2017-04-11 10:32","order_amount":"699.00","order_id":"6"},{"order_sn":"9000000000001901","order_state":"30","order_state_text":"待收货","store_avatar":"http://cs.j.cqxueao.cn/data/upload/shop//store/05448792822646983_sm.jpg","store_name":"杨树的测试店铺","add_time":"2017-04-11 10:35","order_amount":"2097.00","order_id":"7"}]
     */

    private int code;
    private String msg;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * order_sn : 9000000000001801
         * order_state : 20
         * order_state_text : 待发货
         * store_avatar : http://cs.j.cqxueao.cn/data/upload/shop//store/05448792822646983_sm.jpg
         * store_name : 杨树的测试店铺
         * add_time : 2017-04-11 10:32
         * order_amount : 699.00
         * order_id : 6
         */

        private String order_sn;
        private int order_state;
        private String order_state_text;
        private String store_avatar;
        private String store_name;
        private String add_time;
        private String order_amount;
        private int order_id;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getOrder_state() {
            return order_state;
        }

        public void setOrder_state(int order_state) {
            this.order_state = order_state;
        }

        public String getOrder_state_text() {
            return order_state_text;
        }

        public void setOrder_state_text(String order_state_text) {
            this.order_state_text = order_state_text;
        }

        public String getStore_avatar() {
            return store_avatar;
        }

        public void setStore_avatar(String store_avatar) {
            this.store_avatar = store_avatar;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }
    }
}

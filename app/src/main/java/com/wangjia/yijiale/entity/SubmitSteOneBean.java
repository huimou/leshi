package com.wangjia.yijiale.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class SubmitSteOneBean {


    /**
     * code : 200
     * msg : 数据请求成功
     * datas : {"store_cart_list":[{"goods_list":[{"cart_id":"163","buyer_id":"10","store_id":"2","store_name":"花花公子","goods_id":"100011","goods_name":"尔卡丹手拿包女士百搭钱包长款真皮拉链大钞夹女包皮夹手包时尚 红色 10寸","goods_price":"299.00","goods_num":"1","goods_image":"2_05354761239976831.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100010","gc_id":"156","transport_id":"0","goods_freight":"10.00","goods_vat":"0","goods_storage":"77","goods_storage_alarm":"10","is_fcode":"0","have_gift":"0","groupbuy_info":null,"xianshi_info":null,"is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","is_chain":"0","goods_spec":"颜色：红色，尺寸：10寸","contractlist":[],"goods_total":"299.00","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/2/2_05354761239976831_240.jpg"},{"cart_id":"164","buyer_id":"10","store_id":"2","store_name":"花花公子","goods_id":"100020","goods_name":"全球购凯特丝蓓","goods_price":"5048.00","goods_num":"1","goods_image":"2_05358208317091611.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100011","gc_id":"156","transport_id":"1","goods_freight":"0.00","goods_vat":"1","goods_storage":"99","goods_storage_alarm":"5","is_fcode":"0","have_gift":"0","groupbuy_info":null,"xianshi_info":null,"is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","is_chain":"0","contractlist":[],"goods_total":"5048.00","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/2/2_05358208317091611_240.jpg"}],"store_goods_total":"5347.00","store_mansong_rule_list":null,"store_voucher_info":[],"store_voucher_list":[],"freight":"1","store_name":"花花公子","store_id":"2"}],"freight_hash":"TwYXYuoOp5fPk1EIxdRTBw-XApsXGniIn5NfB1jM8zjjs-bBdwcC9SjktpZiLCy7SsX0AaB4rr82sIAgQhnnh5UypUl0UvKdxDQ3vv0e-DhjxzK5-xVl9YIbU1QlOYJX2Bul-UBodWa7CUOozXlxcU0r7UbUB9yoOl7xcIYrSUdVvLb5L7tgOXTp-slsvNcmMsqcSwCh_8HZvYf","address_info":{"address_id":"17","member_id":"10","true_name":"vxcxc","province_id":"5","city_id":"98","area_id":"86","post_code":"465453","area_info":null,"address":"sdfgvdfagdfg","tel_phone":"12322143423","mob_phone":null,"is_default":"1","dlyp_id":"0"},"ifshow_offpay":null,"vat_hash":"I_I2IdfiJONeGO-lSw_1FxFNzV1GkTU_Mi6","inv_info":{"content":"不需要发票"},"available_predeposit":null,"available_rc_balance":null,"rpt_list":[],"zk_list":[],"order_amount":"5357.00","rpt_info":[],"address_api":{"state":"success","content":{"2":"10.00"},"no_send_tpl_ids":["1"],"allow_offpay":"0","allow_offpay_batch":{"1":false},"offpay_hash":"yrsl7Jd6h3_talTmz7hTGwbxxEoD_WXyAHyEvFn","offpay_hash_batch":"VaZvaYYqoCcZpYsJdNLEBa_vAROHxfKYWlhyByvY6V3YGM1"},"store_final_total_list":{"2":"5357.00"}}
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
         * store_cart_list : [{"goods_list":[{"cart_id":"163","buyer_id":"10","store_id":"2","store_name":"花花公子","goods_id":"100011","goods_name":"尔卡丹手拿包女士百搭钱包长款真皮拉链大钞夹女包皮夹手包时尚 红色 10寸","goods_price":"299.00","goods_num":"1","goods_image":"2_05354761239976831.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100010","gc_id":"156","transport_id":"0","goods_freight":"10.00","goods_vat":"0","goods_storage":"77","goods_storage_alarm":"10","is_fcode":"0","have_gift":"0","groupbuy_info":null,"xianshi_info":null,"is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","is_chain":"0","goods_spec":"颜色：红色，尺寸：10寸","contractlist":[],"goods_total":"299.00","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/2/2_05354761239976831_240.jpg"},{"cart_id":"164","buyer_id":"10","store_id":"2","store_name":"花花公子","goods_id":"100020","goods_name":"全球购凯特丝蓓","goods_price":"5048.00","goods_num":"1","goods_image":"2_05358208317091611.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100011","gc_id":"156","transport_id":"1","goods_freight":"0.00","goods_vat":"1","goods_storage":"99","goods_storage_alarm":"5","is_fcode":"0","have_gift":"0","groupbuy_info":null,"xianshi_info":null,"is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","is_chain":"0","contractlist":[],"goods_total":"5048.00","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/2/2_05358208317091611_240.jpg"}],"store_goods_total":"5347.00","store_mansong_rule_list":null,"store_voucher_info":[],"store_voucher_list":[],"freight":"1","store_name":"花花公子","store_id":"2"}]
         * freight_hash : TwYXYuoOp5fPk1EIxdRTBw-XApsXGniIn5NfB1jM8zjjs-bBdwcC9SjktpZiLCy7SsX0AaB4rr82sIAgQhnnh5UypUl0UvKdxDQ3vv0e-DhjxzK5-xVl9YIbU1QlOYJX2Bul-UBodWa7CUOozXlxcU0r7UbUB9yoOl7xcIYrSUdVvLb5L7tgOXTp-slsvNcmMsqcSwCh_8HZvYf
         * address_info : {"address_id":"17","member_id":"10","true_name":"vxcxc","province_id":"5","city_id":"98","area_id":"86","post_code":"465453","area_info":null,"address":"sdfgvdfagdfg","tel_phone":"12322143423","mob_phone":null,"is_default":"1","dlyp_id":"0"}
         * ifshow_offpay : null
         * vat_hash : I_I2IdfiJONeGO-lSw_1FxFNzV1GkTU_Mi6
         * inv_info : {"content":"不需要发票"}
         * available_predeposit : null
         * available_rc_balance : null
         * rpt_list : []
         * zk_list : []
         * order_amount : 5357.00
         * rpt_info : []
         * address_api : {"state":"success","content":{"2":"10.00"},"no_send_tpl_ids":["1"],"allow_offpay":"0","allow_offpay_batch":{"1":false},"offpay_hash":"yrsl7Jd6h3_talTmz7hTGwbxxEoD_WXyAHyEvFn","offpay_hash_batch":"VaZvaYYqoCcZpYsJdNLEBa_vAROHxfKYWlhyByvY6V3YGM1"}
         * store_final_total_list : {"2":"5357.00"}
         */

        private String freight_hash;
        private AddressInfoBean address_info;
        private Object ifshow_offpay;
        private String vat_hash;
        private InvInfoBean inv_info;
        private Object available_predeposit;
        private Object available_rc_balance;
        private String order_amount;
        private AddressApiBean address_api;
        private StoreFinalTotalListBean store_final_total_list;
        private List<StoreCartListBean> store_cart_list;
        private List<?> rpt_list;
        private List<?> zk_list;
        private List<?> rpt_info;

        public String getFreight_hash() {
            return freight_hash;
        }

        public void setFreight_hash(String freight_hash) {
            this.freight_hash = freight_hash;
        }

        public AddressInfoBean getAddress_info() {
            return address_info;
        }

        public void setAddress_info(AddressInfoBean address_info) {
            this.address_info = address_info;
        }

        public Object getIfshow_offpay() {
            return ifshow_offpay;
        }

        public void setIfshow_offpay(Object ifshow_offpay) {
            this.ifshow_offpay = ifshow_offpay;
        }

        public String getVat_hash() {
            return vat_hash;
        }

        public void setVat_hash(String vat_hash) {
            this.vat_hash = vat_hash;
        }

        public InvInfoBean getInv_info() {
            return inv_info;
        }

        public void setInv_info(InvInfoBean inv_info) {
            this.inv_info = inv_info;
        }

        public Object getAvailable_predeposit() {
            return available_predeposit;
        }

        public void setAvailable_predeposit(Object available_predeposit) {
            this.available_predeposit = available_predeposit;
        }

        public Object getAvailable_rc_balance() {
            return available_rc_balance;
        }

        public void setAvailable_rc_balance(Object available_rc_balance) {
            this.available_rc_balance = available_rc_balance;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public AddressApiBean getAddress_api() {
            return address_api;
        }

        public void setAddress_api(AddressApiBean address_api) {
            this.address_api = address_api;
        }

        public StoreFinalTotalListBean getStore_final_total_list() {
            return store_final_total_list;
        }

        public void setStore_final_total_list(StoreFinalTotalListBean store_final_total_list) {
            this.store_final_total_list = store_final_total_list;
        }

        public List<StoreCartListBean> getStore_cart_list() {
            return store_cart_list;
        }

        public void setStore_cart_list(List<StoreCartListBean> store_cart_list) {
            this.store_cart_list = store_cart_list;
        }

        public List<?> getRpt_list() {
            return rpt_list;
        }

        public void setRpt_list(List<?> rpt_list) {
            this.rpt_list = rpt_list;
        }

        public List<?> getZk_list() {
            return zk_list;
        }

        public void setZk_list(List<?> zk_list) {
            this.zk_list = zk_list;
        }

        public List<?> getRpt_info() {
            return rpt_info;
        }

        public void setRpt_info(List<?> rpt_info) {
            this.rpt_info = rpt_info;
        }

        public static class AddressInfoBean {
            /**
             * address_id : 17
             * member_id : 10
             * true_name : vxcxc
             * province_id : 5
             * city_id : 98
             * area_id : 86
             * post_code : 465453
             * area_info : null
             * address : sdfgvdfagdfg
             * tel_phone : 12322143423
             * mob_phone : null
             * is_default : 1
             * dlyp_id : 0
             */

            private String address_id;
            private String member_id;
            private String true_name;
            private String province_id;
            private String city_id;
            private String area_id;
            private String post_code;
            private String area_info;
            private String address;
            private String tel_phone;
            private String mob_phone;
            private String is_default;
            private String dlyp_id;

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getTrue_name() {
                return true_name;
            }

            public void setTrue_name(String true_name) {
                this.true_name = true_name;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public String getPost_code() {
                return post_code;
            }

            public void setPost_code(String post_code) {
                this.post_code = post_code;
            }

            public String getArea_info() {
                return area_info;
            }

            public void setArea_info(String area_info) {
                this.area_info = area_info;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTel_phone() {
                return tel_phone;
            }

            public void setTel_phone(String tel_phone) {
                this.tel_phone = tel_phone;
            }

            public String getMob_phone() {
                return mob_phone;
            }

            public void setMob_phone(String mob_phone) {
                this.mob_phone = mob_phone;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public String getDlyp_id() {
                return dlyp_id;
            }

            public void setDlyp_id(String dlyp_id) {
                this.dlyp_id = dlyp_id;
            }
        }

        public static class InvInfoBean {
            /**
             * content : 不需要发票
             */

            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class AddressApiBean {
            /**
             * state : success
             * content : {"2":"10.00"}
             * no_send_tpl_ids : ["1"]
             * allow_offpay : 0
             * allow_offpay_batch : {"1":false}
             * offpay_hash : yrsl7Jd6h3_talTmz7hTGwbxxEoD_WXyAHyEvFn
             * offpay_hash_batch : VaZvaYYqoCcZpYsJdNLEBa_vAROHxfKYWlhyByvY6V3YGM1
             */

            private String state;
            private ContentBean content;
            private String allow_offpay;
            private AllowOffpayBatchBean allow_offpay_batch;
            private String offpay_hash;
            private String offpay_hash_batch;
            private List<String> no_send_tpl_ids;

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public ContentBean getContent() {
                return content;
            }

            public void setContent(ContentBean content) {
                this.content = content;
            }

            public String getAllow_offpay() {
                return allow_offpay;
            }

            public void setAllow_offpay(String allow_offpay) {
                this.allow_offpay = allow_offpay;
            }

            public AllowOffpayBatchBean getAllow_offpay_batch() {
                return allow_offpay_batch;
            }

            public void setAllow_offpay_batch(AllowOffpayBatchBean allow_offpay_batch) {
                this.allow_offpay_batch = allow_offpay_batch;
            }

            public String getOffpay_hash() {
                return offpay_hash;
            }

            public void setOffpay_hash(String offpay_hash) {
                this.offpay_hash = offpay_hash;
            }

            public String getOffpay_hash_batch() {
                return offpay_hash_batch;
            }

            public void setOffpay_hash_batch(String offpay_hash_batch) {
                this.offpay_hash_batch = offpay_hash_batch;
            }

            public List<String> getNo_send_tpl_ids() {
                return no_send_tpl_ids;
            }

            public void setNo_send_tpl_ids(List<String> no_send_tpl_ids) {
                this.no_send_tpl_ids = no_send_tpl_ids;
            }

            public static class ContentBean {
                /**
                 * 2 : 10.00
                 */

                @SerializedName("2")
                private String _$2;

                public String get_$2() {
                    return _$2;
                }

                public void set_$2(String _$2) {
                    this._$2 = _$2;
                }
            }

            public static class AllowOffpayBatchBean {
                /**
                 * 1 : false
                 */

                @SerializedName("1")
                private boolean _$1;

                public boolean is_$1() {
                    return _$1;
                }

                public void set_$1(boolean _$1) {
                    this._$1 = _$1;
                }
            }
        }

        public static class StoreFinalTotalListBean {
            /**
             * 2 : 5357.00
             */

            @SerializedName("2")
            private String _$2;

            public String get_$2() {
                return _$2;
            }

            public void set_$2(String _$2) {
                this._$2 = _$2;
            }
        }

        public static class StoreCartListBean {
            /**
             * goods_list : [{"cart_id":"163","buyer_id":"10","store_id":"2","store_name":"花花公子","goods_id":"100011","goods_name":"尔卡丹手拿包女士百搭钱包长款真皮拉链大钞夹女包皮夹手包时尚 红色 10寸","goods_price":"299.00","goods_num":"1","goods_image":"2_05354761239976831.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100010","gc_id":"156","transport_id":"0","goods_freight":"10.00","goods_vat":"0","goods_storage":"77","goods_storage_alarm":"10","is_fcode":"0","have_gift":"0","groupbuy_info":null,"xianshi_info":null,"is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","is_chain":"0","goods_spec":"颜色：红色，尺寸：10寸","contractlist":[],"goods_total":"299.00","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/2/2_05354761239976831_240.jpg"},{"cart_id":"164","buyer_id":"10","store_id":"2","store_name":"花花公子","goods_id":"100020","goods_name":"全球购凯特丝蓓","goods_price":"5048.00","goods_num":"1","goods_image":"2_05358208317091611.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100011","gc_id":"156","transport_id":"1","goods_freight":"0.00","goods_vat":"1","goods_storage":"99","goods_storage_alarm":"5","is_fcode":"0","have_gift":"0","groupbuy_info":null,"xianshi_info":null,"is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","is_chain":"0","contractlist":[],"goods_total":"5048.00","goods_image_url":"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/2/2_05358208317091611_240.jpg"}]
             * store_goods_total : 5347.00
             * store_mansong_rule_list : null
             * store_voucher_info : []
             * store_voucher_list : []
             * freight : 1
             * store_name : 花花公子
             * store_id : 2
             */

            private String store_goods_total;
            private Object store_mansong_rule_list;
            private String freight;
            private String store_name;
            private String store_id;
            private List<GoodsListBean> goods_list;
            private List<?> store_voucher_info;
            private List<?> store_voucher_list;

            public String getStore_goods_total() {
                return store_goods_total;
            }

            public void setStore_goods_total(String store_goods_total) {
                this.store_goods_total = store_goods_total;
            }

            public Object getStore_mansong_rule_list() {
                return store_mansong_rule_list;
            }

            public void setStore_mansong_rule_list(Object store_mansong_rule_list) {
                this.store_mansong_rule_list = store_mansong_rule_list;
            }

            public String getFreight() {
                return freight;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public List<GoodsListBean> getGoods_list() {
                return goods_list;
            }

            public void setGoods_list(List<GoodsListBean> goods_list) {
                this.goods_list = goods_list;
            }

            public List<?> getStore_voucher_info() {
                return store_voucher_info;
            }

            public void setStore_voucher_info(List<?> store_voucher_info) {
                this.store_voucher_info = store_voucher_info;
            }

            public List<?> getStore_voucher_list() {
                return store_voucher_list;
            }

            public void setStore_voucher_list(List<?> store_voucher_list) {
                this.store_voucher_list = store_voucher_list;
            }

            public static class GoodsListBean {
                /**
                 * cart_id : 163
                 * buyer_id : 10
                 * store_id : 2
                 * store_name : 花花公子
                 * goods_id : 100011
                 * goods_name : 尔卡丹手拿包女士百搭钱包长款真皮拉链大钞夹女包皮夹手包时尚 红色 10寸
                 * goods_price : 299.00
                 * goods_num : 1
                 * goods_image : 2_05354761239976831.jpg
                 * bl_id : 0
                 * state : true
                 * storage_state : true
                 * goods_commonid : 100010
                 * gc_id : 156
                 * transport_id : 0
                 * goods_freight : 10.00
                 * goods_vat : 0
                 * goods_storage : 77
                 * goods_storage_alarm : 10
                 * is_fcode : 0
                 * have_gift : 0
                 * groupbuy_info : null
                 * xianshi_info : null
                 * is_book : 0
                 * book_down_payment : 0.00
                 * book_final_payment : 0.00
                 * book_down_time : 0
                 * is_chain : 0
                 * goods_spec : 颜色：红色，尺寸：10寸
                 * contractlist : []
                 * goods_total : 299.00
                 * goods_image_url : http://cs.j.cqxueao.cn/data/upload/shop/store/goods/2/2_05354761239976831_240.jpg
                 */

                private String cart_id;
                private String buyer_id;
                private String store_id;
                private String store_name;
                private String goods_id;
                private String goods_name;
                private String goods_price;
                private String goods_num;
                private String goods_image;
                private String bl_id;
                private boolean state;
                private boolean storage_state;
                private String goods_commonid;
                private String gc_id;
                private String transport_id;
                private String goods_freight;
                private String goods_vat;
                private String goods_storage;
                private String goods_storage_alarm;
                private String is_fcode;
                private String have_gift;
                private Object groupbuy_info;
                private Object xianshi_info;
                private String is_book;
                private String book_down_payment;
                private String book_final_payment;
                private String book_down_time;
                private String is_chain;
                private String goods_spec;
                private String goods_total;
                private String goods_image_url;
                private List<?> contractlist;

                public String getCart_id() {
                    return cart_id;
                }

                public void setCart_id(String cart_id) {
                    this.cart_id = cart_id;
                }

                public String getBuyer_id() {
                    return buyer_id;
                }

                public void setBuyer_id(String buyer_id) {
                    this.buyer_id = buyer_id;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
                    this.store_id = store_id;
                }

                public String getStore_name() {
                    return store_name;
                }

                public void setStore_name(String store_name) {
                    this.store_name = store_name;
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

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getBl_id() {
                    return bl_id;
                }

                public void setBl_id(String bl_id) {
                    this.bl_id = bl_id;
                }

                public boolean isState() {
                    return state;
                }

                public void setState(boolean state) {
                    this.state = state;
                }

                public boolean isStorage_state() {
                    return storage_state;
                }

                public void setStorage_state(boolean storage_state) {
                    this.storage_state = storage_state;
                }

                public String getGoods_commonid() {
                    return goods_commonid;
                }

                public void setGoods_commonid(String goods_commonid) {
                    this.goods_commonid = goods_commonid;
                }

                public String getGc_id() {
                    return gc_id;
                }

                public void setGc_id(String gc_id) {
                    this.gc_id = gc_id;
                }

                public String getTransport_id() {
                    return transport_id;
                }

                public void setTransport_id(String transport_id) {
                    this.transport_id = transport_id;
                }

                public String getGoods_freight() {
                    return goods_freight;
                }

                public void setGoods_freight(String goods_freight) {
                    this.goods_freight = goods_freight;
                }

                public String getGoods_vat() {
                    return goods_vat;
                }

                public void setGoods_vat(String goods_vat) {
                    this.goods_vat = goods_vat;
                }

                public String getGoods_storage() {
                    return goods_storage;
                }

                public void setGoods_storage(String goods_storage) {
                    this.goods_storage = goods_storage;
                }

                public String getGoods_storage_alarm() {
                    return goods_storage_alarm;
                }

                public void setGoods_storage_alarm(String goods_storage_alarm) {
                    this.goods_storage_alarm = goods_storage_alarm;
                }

                public String getIs_fcode() {
                    return is_fcode;
                }

                public void setIs_fcode(String is_fcode) {
                    this.is_fcode = is_fcode;
                }

                public String getHave_gift() {
                    return have_gift;
                }

                public void setHave_gift(String have_gift) {
                    this.have_gift = have_gift;
                }

                public Object getGroupbuy_info() {
                    return groupbuy_info;
                }

                public void setGroupbuy_info(Object groupbuy_info) {
                    this.groupbuy_info = groupbuy_info;
                }

                public Object getXianshi_info() {
                    return xianshi_info;
                }

                public void setXianshi_info(Object xianshi_info) {
                    this.xianshi_info = xianshi_info;
                }

                public String getIs_book() {
                    return is_book;
                }

                public void setIs_book(String is_book) {
                    this.is_book = is_book;
                }

                public String getBook_down_payment() {
                    return book_down_payment;
                }

                public void setBook_down_payment(String book_down_payment) {
                    this.book_down_payment = book_down_payment;
                }

                public String getBook_final_payment() {
                    return book_final_payment;
                }

                public void setBook_final_payment(String book_final_payment) {
                    this.book_final_payment = book_final_payment;
                }

                public String getBook_down_time() {
                    return book_down_time;
                }

                public void setBook_down_time(String book_down_time) {
                    this.book_down_time = book_down_time;
                }

                public String getIs_chain() {
                    return is_chain;
                }

                public void setIs_chain(String is_chain) {
                    this.is_chain = is_chain;
                }

                public String getGoods_spec() {
                    return goods_spec;
                }

                public void setGoods_spec(String goods_spec) {
                    this.goods_spec = goods_spec;
                }

                public String getGoods_total() {
                    return goods_total;
                }

                public void setGoods_total(String goods_total) {
                    this.goods_total = goods_total;
                }

                public String getGoods_image_url() {
                    return goods_image_url;
                }

                public void setGoods_image_url(String goods_image_url) {
                    this.goods_image_url = goods_image_url;
                }

                public List<?> getContractlist() {
                    return contractlist;
                }

                public void setContractlist(List<?> contractlist) {
                    this.contractlist = contractlist;
                }
            }
        }
    }
}

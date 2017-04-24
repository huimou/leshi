package com.wangjia.yijiale.entity;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class ShowVipBean {


    /**
     * code : 200
     * msg : 信息获取成功
     * datas : {"member_info":{"available_predeposit":"9994247.00"},"mbpayments":[{"payment_id":"1","payment_code":"alipay","payment_name":"支付宝","payment_config":{"alipay_account":"365882903@qq.com","alipay_key":"idontknow","alipay_partner":"zhs19921012"},"payment_state":"1","logo":"http://cs.j.cqxueao.cn/data/upload/shop/common/alipay.png"},{"payment_id":"2","payment_code":"wxpay","payment_name":"微信支付","payment_config":{"wxpay_appid":"123","wxpay_appsecret":null,"wxpay_appkey":null,"wxpay_partnerid":"123","wxpay_partnerkey":"123"},"payment_state":"1","logo":"http://cs.j.cqxueao.cn/data/upload/shop/common/wxpay.png"}]}
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
         * member_info : {"available_predeposit":"9994247.00"}
         * mbpayments : [{"payment_id":"1","payment_code":"alipay","payment_name":"支付宝","payment_config":{"alipay_account":"365882903@qq.com","alipay_key":"idontknow","alipay_partner":"zhs19921012"},"payment_state":"1","logo":"http://cs.j.cqxueao.cn/data/upload/shop/common/alipay.png"},{"payment_id":"2","payment_code":"wxpay","payment_name":"微信支付","payment_config":{"wxpay_appid":"123","wxpay_appsecret":null,"wxpay_appkey":null,"wxpay_partnerid":"123","wxpay_partnerkey":"123"},"payment_state":"1","logo":"http://cs.j.cqxueao.cn/data/upload/shop/common/wxpay.png"}]
         */

        private MemberInfoBean member_info;
        private List<MbpaymentsBean> mbpayments;

        public MemberInfoBean getMember_info() {
            return member_info;
        }

        public void setMember_info(MemberInfoBean member_info) {
            this.member_info = member_info;
        }

        public List<MbpaymentsBean> getMbpayments() {
            return mbpayments;
        }

        public void setMbpayments(List<MbpaymentsBean> mbpayments) {
            this.mbpayments = mbpayments;
        }

        public static class MemberInfoBean {
            /**
             * available_predeposit : 9994247.00
             */

            private String available_predeposit;

            public String getAvailable_predeposit() {
                return available_predeposit;
            }

            public void setAvailable_predeposit(String available_predeposit) {
                this.available_predeposit = available_predeposit;
            }
        }

        public static class MbpaymentsBean {
            /**
             * payment_id : 1
             * payment_code : alipay
             * payment_name : 支付宝
             * payment_config : {"alipay_account":"365882903@qq.com","alipay_key":"idontknow","alipay_partner":"zhs19921012"}
             * payment_state : 1
             * logo : http://cs.j.cqxueao.cn/data/upload/shop/common/alipay.png
             */

            private int payment_id;
            private String payment_code;
            private String payment_name;
            private PaymentConfigBean payment_config;
            private int payment_state;
            private String logo;

            public int getPayment_id() {
                return payment_id;
            }

            public void setPayment_id(int payment_id) {
                this.payment_id = payment_id;
            }

            public String getPayment_code() {
                return payment_code;
            }

            public void setPayment_code(String payment_code) {
                this.payment_code = payment_code;
            }

            public String getPayment_name() {
                return payment_name;
            }

            public void setPayment_name(String payment_name) {
                this.payment_name = payment_name;
            }

            public PaymentConfigBean getPayment_config() {
                return payment_config;
            }

            public void setPayment_config(PaymentConfigBean payment_config) {
                this.payment_config = payment_config;
            }

            public int getPayment_state() {
                return payment_state;
            }

            public void setPayment_state(int payment_state) {
                this.payment_state = payment_state;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public static class PaymentConfigBean {
                /**
                 * alipay_account : 365882903@qq.com
                 * alipay_key : idontknow
                 * alipay_partner : zhs19921012
                 */

                private String alipay_account;
                private String alipay_key;
                private String alipay_partner;

                public String getAlipay_account() {
                    return alipay_account;
                }

                public void setAlipay_account(String alipay_account) {
                    this.alipay_account = alipay_account;
                }

                public String getAlipay_key() {
                    return alipay_key;
                }

                public void setAlipay_key(String alipay_key) {
                    this.alipay_key = alipay_key;
                }

                public String getAlipay_partner() {
                    return alipay_partner;
                }

                public void setAlipay_partner(String alipay_partner) {
                    this.alipay_partner = alipay_partner;
                }
            }
        }
    }
}

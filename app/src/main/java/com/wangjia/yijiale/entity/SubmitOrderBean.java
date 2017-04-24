package com.wangjia.yijiale.entity;

/**
 * Created by kevin on 2016/10/26
 */

public class SubmitOrderBean {


    /**
     * code : 200
     * msg : 下单成功
     * datas : {"alipay_str":"alipay_sdk=alipay-sdk-php-20161101&app_id=2016080300159375&biz_content=%7B%22body%22%3A%22%E5%AE%9E%E7%89%A9%E8%AE%A2%E5%8D%95_540545927864675013%22%2C%22subject%22%3A+%22%E5%AE%9E%E7%89%A9%E8%AE%A2%E5%8D%95_540545927864675013%22%2C%22out_trade_no%22%3A+%22540545927864675013%22%2C%22passback_params%22%3A+%22order_type%253Dreal_order%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%22699.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fcs.j.cqxueao.cn%2Fmobile%2Fapi%2Fpayment%2Falipayapp%2Fnotify_url.php&sign_type=RSA2×tamp=2017-04-19+15%3A50%3A52&version=1.0&sign=VRHWdt1MsuqmrCKxhzwX3zlYBJEhIccYp9tHrJ0C5MhqNU0AIG%2BPpSSSvGnEcwaUOxB7m8aBW%2FAOnHqENayfZN5Jz7znQgX7%2FXiOPsyXdLcmUCoLauJ9C3XjzuldKWsSbL4YP%2FumVcgxrlrPS506nHbj0SkPOo4MGayxhlhwwWygKGKYpfWUhYgWURxOszpFWVVAS19JIbEH9F36z%2BoqLJVlfl65Pzft3TtDBriDrg7X1uYnDmeQSMZ6K1wedSN%2F%2F%2FADjnviiBGEE0u%2BqxU9aayy07eH%2B%2FlGuBl7ctJPtg1c6beKJkNQ%2B2IrW4Z8LP4Z%2FVpD4UoJyT4U98ZP4v%2F0Ow%3D%3D"}
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
         * alipay_str : alipay_sdk=alipay-sdk-php-20161101&app_id=2016080300159375&biz_content=%7B%22body%22%3A%22%E5%AE%9E%E7%89%A9%E8%AE%A2%E5%8D%95_540545927864675013%22%2C%22subject%22%3A+%22%E5%AE%9E%E7%89%A9%E8%AE%A2%E5%8D%95_540545927864675013%22%2C%22out_trade_no%22%3A+%22540545927864675013%22%2C%22passback_params%22%3A+%22order_type%253Dreal_order%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%22699.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fcs.j.cqxueao.cn%2Fmobile%2Fapi%2Fpayment%2Falipayapp%2Fnotify_url.php&sign_type=RSA2×tamp=2017-04-19+15%3A50%3A52&version=1.0&sign=VRHWdt1MsuqmrCKxhzwX3zlYBJEhIccYp9tHrJ0C5MhqNU0AIG%2BPpSSSvGnEcwaUOxB7m8aBW%2FAOnHqENayfZN5Jz7znQgX7%2FXiOPsyXdLcmUCoLauJ9C3XjzuldKWsSbL4YP%2FumVcgxrlrPS506nHbj0SkPOo4MGayxhlhwwWygKGKYpfWUhYgWURxOszpFWVVAS19JIbEH9F36z%2BoqLJVlfl65Pzft3TtDBriDrg7X1uYnDmeQSMZ6K1wedSN%2F%2F%2FADjnviiBGEE0u%2BqxU9aayy07eH%2B%2FlGuBl7ctJPtg1c6beKJkNQ%2B2IrW4Z8LP4Z%2FVpD4UoJyT4U98ZP4v%2F0Ow%3D%3D
         */

        private String alipay_str;

        public String getAlipay_str() {
            return alipay_str;
        }

        public void setAlipay_str(String alipay_str) {
            this.alipay_str = alipay_str;
        }
    }
}

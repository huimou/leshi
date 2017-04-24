package com.wangjia.yijiale.entity;

/**
 * Created by kevin on 2016/10/26
 */

public class VipSubmitBean {


    /**
     * code : 200
     * msg : 充值记录添加成功
     * datas : {"pdr_sn":"360545334760459000","pdr_member_id":"13","pdr_member_name":"13272950223","pdr_amount":100,"pdr_add_time":1491990760}
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
         * pdr_sn : 360545334760459000
         * pdr_member_id : 13
         * pdr_member_name : 13272950223
         * pdr_amount : 100
         * pdr_add_time : 1491990760
         */

        private String pdr_sn;
        private String pdr_member_id;
        private String pdr_member_name;
        private double pdr_amount;
        private int pdr_add_time;

        public String getPdr_sn() {
            return pdr_sn;
        }

        public void setPdr_sn(String pdr_sn) {
            this.pdr_sn = pdr_sn;
        }

        public String getPdr_member_id() {
            return pdr_member_id;
        }

        public void setPdr_member_id(String pdr_member_id) {
            this.pdr_member_id = pdr_member_id;
        }

        public String getPdr_member_name() {
            return pdr_member_name;
        }

        public void setPdr_member_name(String pdr_member_name) {
            this.pdr_member_name = pdr_member_name;
        }

        public double getPdr_amount() {
            return pdr_amount;
        }

        public void setPdr_amount(double pdr_amount) {
            this.pdr_amount = pdr_amount;
        }

        public int getPdr_add_time() {
            return pdr_add_time;
        }

        public void setPdr_add_time(int pdr_add_time) {
            this.pdr_add_time = pdr_add_time;
        }
    }
}

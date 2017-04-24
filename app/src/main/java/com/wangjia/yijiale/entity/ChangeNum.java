package com.wangjia.yijiale.entity;

import java.io.Serializable;

/**
 * Created by kevin on 2016/10/26
 */

public class ChangeNum implements Serializable{

    /**
     * code : 200
     * msg : 添加成功
     * datas : [{"goods_zprice":501,"goods_znum":7}]
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

    public static class DatasBean implements Serializable {
        /**
         * goods_zprice : 501
         * goods_znum : 7
         */

        private double goods_zprice;
        private int goods_znum;

        public double getGoods_zprice() {
            return goods_zprice;
        }

        public void setGoods_zprice(double goods_zprice) {
            this.goods_zprice = goods_zprice;
        }

        public int getGoods_znum() {
            return goods_znum;
        }

        public void setGoods_znum(int goods_znum) {
            this.goods_znum = goods_znum;
        }
    }
}

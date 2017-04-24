package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class Banner {

    /**
     * code : 1
     * msg : 获取数据成功
     * datas : [{"ad_type":"1","ad_id":"1","img_url":"http://192.168.0.10:182/data/upload/shop/adv/05352193184582740.png","adv_title":"图1"}]
     */

    private String code;
    private String msg;
    /**
     * ad_type : 1
     * ad_id : 1
     * img_url : http://192.168.0.10:182/data/upload/shop/adv/05352193184582740.png
     * adv_title : 图1
     */

    private List<DatasBean> datas;

    public static Banner objectFromData(String str) {

        return new Gson().fromJson(str, Banner.class);
    }

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String ad_type;
        private String ad_id;
        private String img_url;
        private String adv_title;
        private String ad_link;
        private String ad_content;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public String getAd_link() {
            return ad_link;
        }

        public void setAd_link(String ad_link) {
            this.ad_link = ad_link;
        }

        public String getAd_content() {
            return ad_content;
        }

        public void setAd_content(String ad_content) {
            this.ad_content = ad_content;
        }

        public String getAd_type() {
            return ad_type;
        }

        public void setAd_type(String ad_type) {
            this.ad_type = ad_type;
        }

        public String getAd_id() {
            return ad_id;
        }

        public void setAd_id(String ad_id) {
            this.ad_id = ad_id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getAdv_title() {
            return adv_title;
        }

        public void setAdv_title(String adv_title) {
            this.adv_title = adv_title;
        }
    }
}

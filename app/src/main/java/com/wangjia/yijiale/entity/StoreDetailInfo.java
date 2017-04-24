package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class StoreDetailInfo {


    /**
     * code : 200
     * msg : 返回商家店铺首页！
     * datas : {"store_id":"2","store_name":"花花公子","store_qc_code":"http://localhost/data/upload/shop/store/05354582644316773_sm.png","is_favorate":false,"mb_sliders":[{"img":"05354741965056744.png","type":2,"link":100010,"imgUrl":"http://localhost/data/upload/shop/store/05354741965056744.png"},{"img":"05354742028391029.png","type":2,"link":"","imgUrl":"http://localhost/data/upload/shop/store/05354742028391029.png"},{"img":"05354742080652933.png","type":2,"link":"","imgUrl":"http://localhost/data/upload/shop/store/05354742080652933.png"},{"img":"05354742112945178.png","type":2,"link":"","imgUrl":"http://localhost/data/upload/shop/store/05354742112945178.png"},{"img":"05354742152090077.png","type":2,"link":"","imgUrl":"http://localhost/data/upload/shop/store/05354742152090077.png"}]}
     */

    private int code;
    private String msg;
    private DatasBean datas;

    public static StoreDetailInfo objectFromData(String str) {

        return new Gson().fromJson(str, StoreDetailInfo.class);
    }

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
         * store_id : 2
         * store_name : 花花公子
         * store_qc_code : http://localhost/data/upload/shop/store/05354582644316773_sm.png
         * is_favorate : false
         * mb_sliders : [{"img":"05354741965056744.png","type":2,"link":100010,"imgUrl":"http://localhost/data/upload/shop/store/05354741965056744.png"},{"img":"05354742028391029.png","type":2,"link":"","imgUrl":"http://localhost/data/upload/shop/store/05354742028391029.png"},{"img":"05354742080652933.png","type":2,"link":"","imgUrl":"http://localhost/data/upload/shop/store/05354742080652933.png"},{"img":"05354742112945178.png","type":2,"link":"","imgUrl":"http://localhost/data/upload/shop/store/05354742112945178.png"},{"img":"05354742152090077.png","type":2,"link":"","imgUrl":"http://localhost/data/upload/shop/store/05354742152090077.png"}]
         */

        private int store_id;
        private String store_name;
        private String store_qc_code;
        private int is_favorate;
        private List<MbSlidersBean> mb_sliders;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

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

        public String getStore_qc_code() {
            return store_qc_code;
        }

        public void setStore_qc_code(String store_qc_code) {
            this.store_qc_code = store_qc_code;
        }

        public int getIs_favorate() {
            return is_favorate;
        }

        public void setIs_favorate(int is_favorate) {
            this.is_favorate = is_favorate;
        }

        public List<MbSlidersBean> getMb_sliders() {
            return mb_sliders;
        }

        public void setMb_sliders(List<MbSlidersBean> mb_sliders) {
            this.mb_sliders = mb_sliders;
        }

        public static class MbSlidersBean {
            /**
             * img : 05354741965056744.png
             * type : 2
             * link : 100010
             * imgUrl : http://localhost/data/upload/shop/store/05354741965056744.png
             */

            private String img;
            private int type;
            private String link;
            private String imgUrl;

            public static MbSlidersBean objectFromData(String str) {

                return new Gson().fromJson(str, MbSlidersBean.class);
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}

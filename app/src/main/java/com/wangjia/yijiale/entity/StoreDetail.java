package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class StoreDetail {

    /**
     * code : 200
     * datas : {"store_info":{"store_id":"2","addr":"","store_phone":"18723151485","store_zy":"专业女装，专业女包，品质有保障","store_name":"花花公子","member_id":"7","store_avatar":"http://192.168.0.10:182/data/upload/shop/store/05354582644316773_sm.png","goods_count":1,"store_collect":1,"is_favorate":true,"mb_sliders":[{"img":"05354741965056744.png","type":2,"link":100010,"imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354741965056744.png"},{"img":"05354742028391029.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742028391029.png"},{"img":"05354742080652933.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742080652933.png"},{"img":"05354742112945178.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742112945178.png"},{"img":"05354742152090077.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742152090077.png"}]},"rec_goods_list_count":1,"rec_goods_list":[{"goods_id":"100010","store_id":"2","store_name":"花花公子","goods_name":"尔卡丹手拿包女士百搭钱包长款真皮拉链大钞夹女包皮夹手包时尚","goods_price":"299.00","goods_marketprice":"599.00","goods_image":"2_05354761239976831.jpg","goods_salenum":"0","evaluation_good_star":"5","evaluation_count":"0","is_virtual":"0","is_presell":"0","is_fcode":"0","have_gift":"0","goods_addtime":"1482132256","sole_flag":false,"group_flag":false,"xianshi_flag":false,"goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/2/2_05354761239976831_360.jpg"}]}
     */

    private int code;
    /**
     * store_info : {"store_id":"2","addr":"","store_phone":"18723151485","store_zy":"专业女装，专业女包，品质有保障","store_name":"花花公子","member_id":"7","store_avatar":"http://192.168.0.10:182/data/upload/shop/store/05354582644316773_sm.png","goods_count":1,"store_collect":1,"is_favorate":true,"mb_sliders":[{"img":"05354741965056744.png","type":2,"link":100010,"imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354741965056744.png"},{"img":"05354742028391029.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742028391029.png"},{"img":"05354742080652933.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742080652933.png"},{"img":"05354742112945178.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742112945178.png"},{"img":"05354742152090077.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742152090077.png"}]}
     * rec_goods_list_count : 1
     * rec_goods_list : [{"goods_id":"100010","store_id":"2","store_name":"花花公子","goods_name":"尔卡丹手拿包女士百搭钱包长款真皮拉链大钞夹女包皮夹手包时尚","goods_price":"299.00","goods_marketprice":"599.00","goods_image":"2_05354761239976831.jpg","goods_salenum":"0","evaluation_good_star":"5","evaluation_count":"0","is_virtual":"0","is_presell":"0","is_fcode":"0","have_gift":"0","goods_addtime":"1482132256","sole_flag":false,"group_flag":false,"xianshi_flag":false,"goods_image_url":"http://192.168.0.10:182/data/upload/shop/store/goods/2/2_05354761239976831_360.jpg"}]
     */

    private DatasBean datas;

    public static StoreDetail objectFromData(String str) {

        return new Gson().fromJson(str, StoreDetail.class);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
         * addr :
         * store_phone : 18723151485
         * store_zy : 专业女装，专业女包，品质有保障
         * store_name : 花花公子
         * member_id : 7
         * store_avatar : http://192.168.0.10:182/data/upload/shop/store/05354582644316773_sm.png
         * goods_count : 1
         * store_collect : 1
         * is_favorate : true
         * mb_sliders : [{"img":"05354741965056744.png","type":2,"link":100010,"imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354741965056744.png"},{"img":"05354742028391029.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742028391029.png"},{"img":"05354742080652933.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742080652933.png"},{"img":"05354742112945178.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742112945178.png"},{"img":"05354742152090077.png","type":2,"link":"","imgUrl":"http://192.168.0.10:182/data/upload/shop/store/05354742152090077.png"}]
         */

        private StoreInfoBean store_info;
        private int rec_goods_list_count;
        /**
         * goods_id : 100010
         * store_id : 2
         * store_name : 花花公子
         * goods_name : 尔卡丹手拿包女士百搭钱包长款真皮拉链大钞夹女包皮夹手包时尚
         * goods_price : 299.00
         * goods_marketprice : 599.00
         * goods_image : 2_05354761239976831.jpg
         * goods_salenum : 0
         * evaluation_good_star : 5
         * evaluation_count : 0
         * is_virtual : 0
         * is_presell : 0
         * is_fcode : 0
         * have_gift : 0
         * goods_addtime : 1482132256
         * sole_flag : false
         * group_flag : false
         * xianshi_flag : false
         * goods_image_url : http://192.168.0.10:182/data/upload/shop/store/goods/2/2_05354761239976831_360.jpg
         */

        private List<RecGoodsListBean> rec_goods_list;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public StoreInfoBean getStore_info() {
            return store_info;
        }

        public void setStore_info(StoreInfoBean store_info) {
            this.store_info = store_info;
        }

        public int getRec_goods_list_count() {
            return rec_goods_list_count;
        }

        public void setRec_goods_list_count(int rec_goods_list_count) {
            this.rec_goods_list_count = rec_goods_list_count;
        }

        public List<RecGoodsListBean> getRec_goods_list() {
            return rec_goods_list;
        }

        public void setRec_goods_list(List<RecGoodsListBean> rec_goods_list) {
            this.rec_goods_list = rec_goods_list;
        }

        public static class StoreInfoBean {
            private String store_id;
            private String addr;
            private String store_phone;
            private String store_zy;
            private String store_name;
            private String member_id;
            private String store_avatar;
            private int goods_count;
            private int store_collect;
            private boolean is_favorate;
            /**
             * img : 05354741965056744.png
             * type : 2
             * link : 100010
             * imgUrl : http://192.168.0.10:182/data/upload/shop/store/05354741965056744.png
             */

            private List<MbSlidersBean> mb_sliders;

            public static StoreInfoBean objectFromData(String str) {

                return new Gson().fromJson(str, StoreInfoBean.class);
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getStore_phone() {
                return store_phone;
            }

            public void setStore_phone(String store_phone) {
                this.store_phone = store_phone;
            }

            public String getStore_zy() {
                return store_zy;
            }

            public void setStore_zy(String store_zy) {
                this.store_zy = store_zy;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getStore_avatar() {
                return store_avatar;
            }

            public void setStore_avatar(String store_avatar) {
                this.store_avatar = store_avatar;
            }

            public int getGoods_count() {
                return goods_count;
            }

            public void setGoods_count(int goods_count) {
                this.goods_count = goods_count;
            }

            public int getStore_collect() {
                return store_collect;
            }

            public void setStore_collect(int store_collect) {
                this.store_collect = store_collect;
            }

            public boolean isIs_favorate() {
                return is_favorate;
            }

            public void setIs_favorate(boolean is_favorate) {
                this.is_favorate = is_favorate;
            }

            public List<MbSlidersBean> getMb_sliders() {
                return mb_sliders;
            }

            public void setMb_sliders(List<MbSlidersBean> mb_sliders) {
                this.mb_sliders = mb_sliders;
            }

            public static class MbSlidersBean {
                private String img;
                private int type;
                private int link;
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

                public int getLink() {
                    return link;
                }

                public void setLink(int link) {
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

        public static class RecGoodsListBean {
            private String goods_id;
            private String store_id;
            private String store_name;
            private String goods_name;
            private String goods_price;
            private String goods_marketprice;
            private String goods_image;
            private String goods_salenum;
            private String evaluation_good_star;
            private String evaluation_count;
            private String is_virtual;
            private String is_presell;
            private String is_fcode;
            private String have_gift;
            private String goods_addtime;
            private boolean sole_flag;
            private boolean group_flag;
            private boolean xianshi_flag;
            private String goods_image_url;

            public static RecGoodsListBean objectFromData(String str) {

                return new Gson().fromJson(str, RecGoodsListBean.class);
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getGoods_marketprice() {
                return goods_marketprice;
            }

            public void setGoods_marketprice(String goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getGoods_salenum() {
                return goods_salenum;
            }

            public void setGoods_salenum(String goods_salenum) {
                this.goods_salenum = goods_salenum;
            }

            public String getEvaluation_good_star() {
                return evaluation_good_star;
            }

            public void setEvaluation_good_star(String evaluation_good_star) {
                this.evaluation_good_star = evaluation_good_star;
            }

            public String getEvaluation_count() {
                return evaluation_count;
            }

            public void setEvaluation_count(String evaluation_count) {
                this.evaluation_count = evaluation_count;
            }

            public String getIs_virtual() {
                return is_virtual;
            }

            public void setIs_virtual(String is_virtual) {
                this.is_virtual = is_virtual;
            }

            public String getIs_presell() {
                return is_presell;
            }

            public void setIs_presell(String is_presell) {
                this.is_presell = is_presell;
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

            public String getGoods_addtime() {
                return goods_addtime;
            }

            public void setGoods_addtime(String goods_addtime) {
                this.goods_addtime = goods_addtime;
            }

            public boolean isSole_flag() {
                return sole_flag;
            }

            public void setSole_flag(boolean sole_flag) {
                this.sole_flag = sole_flag;
            }

            public boolean isGroup_flag() {
                return group_flag;
            }

            public void setGroup_flag(boolean group_flag) {
                this.group_flag = group_flag;
            }

            public boolean isXianshi_flag() {
                return xianshi_flag;
            }

            public void setXianshi_flag(boolean xianshi_flag) {
                this.xianshi_flag = xianshi_flag;
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

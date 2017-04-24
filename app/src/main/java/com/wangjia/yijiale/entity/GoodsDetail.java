package com.wangjia.yijiale.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
public class GoodsDetail {


    /**
     * code : 200
     * message : 商品信息
     * data : {"goods":{"goods_id":"248","goods_sn":"LHY000248","goods_name":"西班牙进口红酒 波拉宏达  木桶之恋干红葡萄酒 750ml","click_count":"232","brand_id":"118","goods_number":"7800","shop_price":179,"market_price":"159.00","promote_price":"0.00","promote_start_date":"0","promote_end_date":"0","goods_desc":[{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379506527.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379684033.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379123866.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379139173.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693380515994.jpg"}],"is_real":"1","is_promote":"0","supplier_id":"0","shopname":"网站自营","shop_logo":"","service_phone":"","shop_atr":209,"promote_atr":30},"goods_gallery":[{"img_url":"http://mall.lohoyun.com/images/201512/goods_img/248_P_1450664609442.jpg","width":378,"height":378},{"img_url":"http://mall.lohoyun.com/images/201512/goods_img/248_P_1450664610182.jpg","width":378,"height":378},{"img_url":"http://mall.lohoyun.com/images/201512/goods_img/248_P_1450891767256.jpg","width":800,"height":800}],"goods_attr":[{"attr_id":"94","attr_type":"1","name":"毫升","values":[{"label":"300ml","price":"10","subid":"848"},{"label":"200ml","price":"10","subid":"849"},{"label":"100ml","price":"10","subid":"850"}]},{"attr_id":"95","attr_type":"1","name":"包装","values":[{"label":"简装","price":"10","subid":"851"},{"label":"精装","price":"10","subid":"852"}]},{"attr_id":"96","attr_type":"1","name":"年份","values":[{"label":"10年","price":"10","subid":"853"},{"label":"20年","price":"10","subid":"854"}]}],"cart_count":"3","goods_stock":[{"goods_attr":["850","851","853"],"product_number":"100"},{"goods_attr":["850","851","854"],"product_number":"200"},{"goods_attr":["850","852","853"],"product_number":"300"},{"goods_attr":["850","852","854"],"product_number":"400"},{"goods_attr":["849","851","853"],"product_number":"500"},{"goods_attr":["849","851","854"],"product_number":"600"},{"goods_attr":["849","852","853"],"product_number":"700"},{"goods_attr":["849","852","854"],"product_number":"800"},{"goods_attr":["848","851","853"],"product_number":"900"},{"goods_attr":["848","851","854"],"product_number":"1000"},{"goods_attr":["848","852","853"],"product_number":"1100"},{"goods_attr":["848","852","854"],"product_number":"1200"}],"is_collect_goods":0}
     */

    private int code;
    private String message;
    /**
     * goods : {"goods_id":"248","goods_sn":"LHY000248","goods_name":"西班牙进口红酒 波拉宏达  木桶之恋干红葡萄酒 750ml","click_count":"232","brand_id":"118","goods_number":"7800","shop_price":179,"market_price":"159.00","promote_price":"0.00","promote_start_date":"0","promote_end_date":"0","goods_desc":[{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379506527.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379684033.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379123866.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379139173.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693380515994.jpg"}],"is_real":"1","is_promote":"0","supplier_id":"0","shopname":"网站自营","shop_logo":"","service_phone":"","shop_atr":209,"promote_atr":30}
     * goods_gallery : [{"img_url":"http://mall.lohoyun.com/images/201512/goods_img/248_P_1450664609442.jpg","width":378,"height":378},{"img_url":"http://mall.lohoyun.com/images/201512/goods_img/248_P_1450664610182.jpg","width":378,"height":378},{"img_url":"http://mall.lohoyun.com/images/201512/goods_img/248_P_1450891767256.jpg","width":800,"height":800}]
     * goods_attr : [{"attr_id":"94","attr_type":"1","name":"毫升","values":[{"label":"300ml","price":"10","subid":"848"},{"label":"200ml","price":"10","subid":"849"},{"label":"100ml","price":"10","subid":"850"}]},{"attr_id":"95","attr_type":"1","name":"包装","values":[{"label":"简装","price":"10","subid":"851"},{"label":"精装","price":"10","subid":"852"}]},{"attr_id":"96","attr_type":"1","name":"年份","values":[{"label":"10年","price":"10","subid":"853"},{"label":"20年","price":"10","subid":"854"}]}]
     * cart_count : 3
     * goods_stock : [{"goods_attr":["850","851","853"],"product_number":"100"},{"goods_attr":["850","851","854"],"product_number":"200"},{"goods_attr":["850","852","853"],"product_number":"300"},{"goods_attr":["850","852","854"],"product_number":"400"},{"goods_attr":["849","851","853"],"product_number":"500"},{"goods_attr":["849","851","854"],"product_number":"600"},{"goods_attr":["849","852","853"],"product_number":"700"},{"goods_attr":["849","852","854"],"product_number":"800"},{"goods_attr":["848","851","853"],"product_number":"900"},{"goods_attr":["848","851","854"],"product_number":"1000"},{"goods_attr":["848","852","853"],"product_number":"1100"},{"goods_attr":["848","852","854"],"product_number":"1200"}]
     * is_collect_goods : 0
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goods_id : 248
         * goods_sn : LHY000248
         * goods_name : 西班牙进口红酒 波拉宏达  木桶之恋干红葡萄酒 750ml
         * click_count : 232
         * brand_id : 118
         * goods_number : 7800
         * shop_price : 179
         * market_price : 159.00
         * promote_price : 0.00
         * promote_start_date : 0
         * promote_end_date : 0
         * goods_desc : [{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379506527.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379684033.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379123866.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379139173.jpg"},{"img_url":"http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693380515994.jpg"}]
         * is_real : 1
         * is_promote : 0
         * supplier_id : 0
         * shopname : 网站自营
         * shop_logo :
         * service_phone :
         * shop_atr : 209
         * promote_atr : 30
         */

        private GoodsBean goods;
        private String cart_count;
        private int is_collect_goods;
        /**
         * img_url : http://mall.lohoyun.com/images/201512/goods_img/248_P_1450664609442.jpg
         * width : 378
         * height : 378
         */

        private List<GoodsGalleryBean> goods_gallery;
        /**
         * attr_id : 94
         * attr_type : 1
         * name : 毫升
         * values : [{"label":"300ml","price":"10","subid":"848"},{"label":"200ml","price":"10","subid":"849"},{"label":"100ml","price":"10","subid":"850"}]
         */

        private List<GoodsAttrBean> goods_attr;
        /**
         * goods_attr : ["850","851","853"]
         * product_number : 100
         */

        private List<GoodsStockBean> goods_stock;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public String getCart_count() {
            return cart_count;
        }

        public void setCart_count(String cart_count) {
            this.cart_count = cart_count;
        }

        public int getIs_collect_goods() {
            return is_collect_goods;
        }

        public void setIs_collect_goods(int is_collect_goods) {
            this.is_collect_goods = is_collect_goods;
        }

        public List<GoodsGalleryBean> getGoods_gallery() {
            return goods_gallery;
        }

        public void setGoods_gallery(List<GoodsGalleryBean> goods_gallery) {
            this.goods_gallery = goods_gallery;
        }

        public List<GoodsAttrBean> getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(List<GoodsAttrBean> goods_attr) {
            this.goods_attr = goods_attr;
        }

        public List<GoodsStockBean> getGoods_stock() {
            return goods_stock;
        }

        public void setGoods_stock(List<GoodsStockBean> goods_stock) {
            this.goods_stock = goods_stock;
        }

        public static class GoodsBean {
            private String goods_id;
            private String goods_sn;
            private String goods_name;
            private String click_count;
            private String brand_id;
            private String goods_number;
            private double shop_price;
            private String market_price;
            private String promote_price;
            private String promote_start_date;
            private String promote_end_date;
            private String is_real;
            private String is_promote;
            private String supplier_id;
            private String shopname;
            private String shop_logo;
            private String service_phone;
            private double shop_atr;
            private double promote_atr;
            /**
             * img_url : http://nian.lohoyun.com/includes/ueditor/php/../../../bdimages/upload1/20151221/1450693379506527.jpg
             */

            private List<GoodsDescBean> goods_desc;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public double getShop_price() {
                return shop_price;
            }

            public void setShop_price(double shop_price) {
                this.shop_price = shop_price;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getPromote_price() {
                return promote_price;
            }

            public void setPromote_price(String promote_price) {
                this.promote_price = promote_price;
            }

            public String getPromote_start_date() {
                return promote_start_date;
            }

            public void setPromote_start_date(String promote_start_date) {
                this.promote_start_date = promote_start_date;
            }

            public String getPromote_end_date() {
                return promote_end_date;
            }

            public void setPromote_end_date(String promote_end_date) {
                this.promote_end_date = promote_end_date;
            }

            public String getIs_real() {
                return is_real;
            }

            public void setIs_real(String is_real) {
                this.is_real = is_real;
            }

            public String getIs_promote() {
                return is_promote;
            }

            public void setIs_promote(String is_promote) {
                this.is_promote = is_promote;
            }

            public String getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(String supplier_id) {
                this.supplier_id = supplier_id;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public String getShop_logo() {
                return shop_logo;
            }

            public void setShop_logo(String shop_logo) {
                this.shop_logo = shop_logo;
            }

            public String getService_phone() {
                return service_phone;
            }

            public void setService_phone(String service_phone) {
                this.service_phone = service_phone;
            }

            public double getShop_atr() {
                return shop_atr;
            }

            public void setShop_atr(double shop_atr) {
                this.shop_atr = shop_atr;
            }

            public double getPromote_atr() {
                return promote_atr;
            }

            public void setPromote_atr(double promote_atr) {
                this.promote_atr = promote_atr;
            }

            public List<GoodsDescBean> getGoods_desc() {
                return goods_desc;
            }

            public void setGoods_desc(List<GoodsDescBean> goods_desc) {
                this.goods_desc = goods_desc;
            }

            public static class GoodsDescBean {
                private String img_url;

                public String getImg_url() {
                    return img_url;
                }

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }
            }
        }

        public static class GoodsGalleryBean {
            private String img_url;
            private int width;
            private int height;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }

        public static class GoodsAttrBean {
            private String attr_id;
            private String attr_type;
            private String name;
            /**
             * label : 300ml
             * price : 10
             * subid : 848
             */

            private List<ValuesBean> values;

            public String getAttr_id() {
                return attr_id;
            }

            public void setAttr_id(String attr_id) {
                this.attr_id = attr_id;
            }

            public String getAttr_type() {
                return attr_type;
            }

            public void setAttr_type(String attr_type) {
                this.attr_type = attr_type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ValuesBean> getValues() {
                return values;
            }

            public void setValues(List<ValuesBean> values) {
                this.values = values;
            }

            public static class ValuesBean {
                private String label;
                private String price;
                private int subid;

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getSubid() {
                    return subid;
                }

                public void setSubid(int subid) {
                    this.subid = subid;
                }
            }
        }

        public static class GoodsStockBean {
            private String product_number;
            private List<String> goods_attr;

            public String getProduct_number() {
                return product_number;
            }

            public void setProduct_number(String product_number) {
                this.product_number = product_number;
            }

            public List<String> getGoods_attr() {
                return goods_attr;
            }

            public void setGoods_attr(List<String> goods_attr) {
                this.goods_attr = goods_attr;
            }
        }
    }
}

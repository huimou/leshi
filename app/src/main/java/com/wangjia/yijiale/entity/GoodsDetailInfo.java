package com.wangjia.yijiale.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
public class GoodsDetailInfo implements Serializable {


    /**
     * code : 200
     * msg : 商品详情
     * datas : {"goods_info":{"goods_id":"100034","goods_name":"欧美菜篮子编织女包羊皮妈咪袋女包真皮大包手提包大容量单肩包包 白色 大","spec_value":[{"spec_n":"颜色","spec_v":[{"spec_id":21,"spec_values":"白色"},{"spec_id":22,"spec_values":"红色"},{"spec_id":23,"spec_values":"灰杏"},{"spec_id":24,"spec_values":"彩兰"},{"spec_id":25,"spec_values":"薰衣草紫"}]},{"spec_n":"尺寸","spec_v":[{"spec_id":26,"spec_values":"大"},{"spec_id":27,"spec_values":"中"},{"spec_id":28,"spec_values":"小"}]}],"goods_body":"<div class=\"default\"><p>\r\n\t<img src=\"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451376982544829_1280.jpg\" alt=\"image\" />\r\n<\/p>\r\n<p>\r\n\t这是一个忒大忒大的宝宝\r\n<\/p><\/div>","mobile_body":"","goods_spec":["21-白色","26-大"],"goods_price":"699.00","goods_marketprice":"700.00","goods_storage":"93","goods_image":["http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377054730931_1280.jpg","http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377616140769_1280.jpg","http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377666143197_1280.jpg"],"spec_list":[{"sign":"21|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100034","goods_id":"100034"},{"sign":"21|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100035","goods_id":"100035"},{"sign":"21|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100036","goods_id":"100036"},{"sign":"22|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100037","goods_id":"100037"},{"sign":"22|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100038","goods_id":"100038"},{"sign":"22|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100039","goods_id":"100039"},{"sign":"23|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100040","goods_id":"100040"},{"sign":"23|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100041","goods_id":"100041"},{"sign":"23|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100042","goods_id":"100042"},{"sign":"24|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100043","goods_id":"100043"},{"sign":"24|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100044","goods_id":"100044"},{"sign":"24|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100045","goods_id":"100045"},{"sign":"25|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100046","goods_id":"100046"},{"sign":"25|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100047","goods_id":"100047"},{"sign":"25|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100048","goods_id":"100048"}],"is_favorate":0}}
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
         * goods_info : {"goods_id":"100034","goods_name":"欧美菜篮子编织女包羊皮妈咪袋女包真皮大包手提包大容量单肩包包 白色 大","spec_value":[{"spec_n":"颜色","spec_v":[{"spec_id":21,"spec_values":"白色"},{"spec_id":22,"spec_values":"红色"},{"spec_id":23,"spec_values":"灰杏"},{"spec_id":24,"spec_values":"彩兰"},{"spec_id":25,"spec_values":"薰衣草紫"}]},{"spec_n":"尺寸","spec_v":[{"spec_id":26,"spec_values":"大"},{"spec_id":27,"spec_values":"中"},{"spec_id":28,"spec_values":"小"}]}],"goods_body":"<div class=\"default\"><p>\r\n\t<img src=\"http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451376982544829_1280.jpg\" alt=\"image\" />\r\n<\/p>\r\n<p>\r\n\t这是一个忒大忒大的宝宝\r\n<\/p><\/div>","mobile_body":"","goods_spec":["21-白色","26-大"],"goods_price":"699.00","goods_marketprice":"700.00","goods_storage":"93","goods_image":["http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377054730931_1280.jpg","http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377616140769_1280.jpg","http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377666143197_1280.jpg"],"spec_list":[{"sign":"21|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100034","goods_id":"100034"},{"sign":"21|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100035","goods_id":"100035"},{"sign":"21|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100036","goods_id":"100036"},{"sign":"22|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100037","goods_id":"100037"},{"sign":"22|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100038","goods_id":"100038"},{"sign":"22|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100039","goods_id":"100039"},{"sign":"23|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100040","goods_id":"100040"},{"sign":"23|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100041","goods_id":"100041"},{"sign":"23|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100042","goods_id":"100042"},{"sign":"24|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100043","goods_id":"100043"},{"sign":"24|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100044","goods_id":"100044"},{"sign":"24|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100045","goods_id":"100045"},{"sign":"25|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100046","goods_id":"100046"},{"sign":"25|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100047","goods_id":"100047"},{"sign":"25|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100048","goods_id":"100048"}],"is_favorate":0}
         */

        private GoodsInfoBean goods_info;

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public static class GoodsInfoBean implements Serializable {
            /**
             * goods_id : 100034
             * goods_name : 欧美菜篮子编织女包羊皮妈咪袋女包真皮大包手提包大容量单肩包包 白色 大
             * spec_value : [{"spec_n":"颜色","spec_v":[{"spec_id":21,"spec_values":"白色"},{"spec_id":22,"spec_values":"红色"},{"spec_id":23,"spec_values":"灰杏"},{"spec_id":24,"spec_values":"彩兰"},{"spec_id":25,"spec_values":"薰衣草紫"}]},{"spec_n":"尺寸","spec_v":[{"spec_id":26,"spec_values":"大"},{"spec_id":27,"spec_values":"中"},{"spec_id":28,"spec_values":"小"}]}]
             * goods_body : <div class="default"><p>
             <img src="http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451376982544829_1280.jpg" alt="image" />
             </p>
             <p>
             这是一个忒大忒大的宝宝
             </p></div>
             * mobile_body :
             * goods_spec : ["21-白色","26-大"]
             * goods_price : 699.00
             * goods_marketprice : 700.00
             * goods_storage : 93
             * goods_image : ["http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377054730931_1280.jpg","http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377616140769_1280.jpg","http://cs.j.cqxueao.cn/data/upload/shop/store/goods/5/5_05451377666143197_1280.jpg"]
             * spec_list : [{"sign":"21|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100034","goods_id":"100034"},{"sign":"21|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100035","goods_id":"100035"},{"sign":"21|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100036","goods_id":"100036"},{"sign":"22|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100037","goods_id":"100037"},{"sign":"22|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100038","goods_id":"100038"},{"sign":"22|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100039","goods_id":"100039"},{"sign":"23|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100040","goods_id":"100040"},{"sign":"23|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100041","goods_id":"100041"},{"sign":"23|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100042","goods_id":"100042"},{"sign":"24|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100043","goods_id":"100043"},{"sign":"24|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100044","goods_id":"100044"},{"sign":"24|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100045","goods_id":"100045"},{"sign":"25|26","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100046","goods_id":"100046"},{"sign":"25|27","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100047","goods_id":"100047"},{"sign":"25|28","url":"http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100048","goods_id":"100048"}]
             * is_favorate : 0
             */

            private String goods_id;
            private String goods_name;
            private String goods_body;
            private String mobile_body;
            private double goods_price;
            private double goods_marketprice;
            private int goods_storage;
            private int is_favorate;
            private List<SpecValueBean> spec_value;
            private List<SpecValueBean> goods_spec;
            private List<String> goods_image;
            private List<SpecListBean> spec_list;

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

            public String getGoods_body() {
                return goods_body;
            }

            public void setGoods_body(String goods_body) {
                this.goods_body = goods_body;
            }

            public String getMobile_body() {
                return mobile_body;
            }

            public void setMobile_body(String mobile_body) {
                this.mobile_body = mobile_body;
            }

            public double getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(double goods_price) {
                this.goods_price = goods_price;
            }

            public double getGoods_marketprice() {
                return goods_marketprice;
            }

            public void setGoods_marketprice(double goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }

            public int getGoods_storage() {
                return goods_storage;
            }

            public void setGoods_storage(int goods_storage) {
                this.goods_storage = goods_storage;
            }

            public int getIs_favorate() {
                return is_favorate;
            }

            public void setIs_favorate(int is_favorate) {
                this.is_favorate = is_favorate;
            }

            public List<SpecValueBean> getSpec_value() {
                return spec_value;
            }

            public void setSpec_value(List<SpecValueBean> spec_value) {
                this.spec_value = spec_value;
            }

            public List<SpecValueBean> getGoods_spec() {
                return goods_spec;
            }

            public void setGoods_spec(List<SpecValueBean> goods_spec) {
                this.goods_spec = goods_spec;
            }

            public List<String> getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(List<String> goods_image) {
                this.goods_image = goods_image;
            }

            public List<SpecListBean> getSpec_list() {
                return spec_list;
            }

            public void setSpec_list(List<SpecListBean> spec_list) {
                this.spec_list = spec_list;
            }

            public static class SpecValueBean implements Serializable {
                /**
                 * spec_n : 颜色
                 * spec_v : [{"spec_id":21,"spec_values":"白色"},{"spec_id":22,"spec_values":"红色"},{"spec_id":23,"spec_values":"灰杏"},{"spec_id":24,"spec_values":"彩兰"},{"spec_id":25,"spec_values":"薰衣草紫"}]
                 */

                private String spec_n;
                private List<SpecVBean> spec_v;

                public String getSpec_n() {
                    return spec_n;
                }

                public void setSpec_n(String spec_n) {
                    this.spec_n = spec_n;
                }

                public List<SpecVBean> getSpec_v() {
                    return spec_v;
                }

                public void setSpec_v(List<SpecVBean> spec_v) {
                    this.spec_v = spec_v;
                }

                public static class SpecVBean {
                    /**
                     * spec_id : 21
                     * spec_values : 白色
                     */

                    private int spec_id;
                    private String spec_values;
                    private boolean select;

                    public boolean isSelect() {
                        return select;
                    }

                    public void setSelect(boolean select) {
                        this.select = select;
                    }

                    public int getSpec_id() {
                        return spec_id;
                    }

                    public void setSpec_id(int spec_id) {
                        this.spec_id = spec_id;
                    }

                    public String getSpec_values() {
                        return spec_values;
                    }

                    public void setSpec_values(String spec_values) {
                        this.spec_values = spec_values;
                    }
                }
            }

            public static class SpecListBean implements Serializable {
                /**
                 * sign : 21|26
                 * url : http://cs.j.cqxueao.cn/index.php?act=goods&op=index&goods_id=100034
                 * goods_id : 100034
                 */

                private String sign;
                private String url;
                private String goods_id;

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }
            }
        }
    }
}

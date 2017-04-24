package com.wangjia.yijiale.entity;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 * 店铺分类
 */

public class StroeClassifyBean {


    /**
     * code : 200
     * msg : 商店分类！
     * datas : {"class_list":[{"sc_id":"1","sc_name":"珠宝/首饰","sc_bail":"0","sc_sort":"8","sc_nav":[{"sc_id":"11","sc_name":"珠宝二级","sc_bail":"0","sc_sort":"0","typeid":"1","showh":"0","picurl":null}]},{"sc_id":"2","sc_name":"服装鞋包","sc_bail":"0","sc_sort":"1","sc_nav":[{"sc_id":"12","sc_name":"服装二级","sc_bail":"0","sc_sort":"255","typeid":"2","showh":"0","picurl":null}]},{"sc_id":"3","sc_name":"3C数码","sc_bail":"0","sc_sort":"2","sc_nav":[]},{"sc_id":"4","sc_name":"美容护理","sc_bail":"0","sc_sort":"3","sc_nav":[]},{"sc_id":"5","sc_name":"家居用品","sc_bail":"0","sc_sort":"4","sc_nav":[]},{"sc_id":"6","sc_name":"食品/保健","sc_bail":"0","sc_sort":"5","sc_nav":[]},{"sc_id":"7","sc_name":"母婴用品","sc_bail":"0","sc_sort":"6","sc_nav":[]},{"sc_id":"8","sc_name":"文体/汽车","sc_bail":"0","sc_sort":"7","sc_nav":[]},{"sc_id":"9","sc_name":"收藏/爱好","sc_bail":"0","sc_sort":"9","sc_nav":[]},{"sc_id":"10","sc_name":"生活/服务","sc_bail":"0","sc_sort":"10","sc_nav":[]}]}
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
        private List<ClassListBean> class_list;

        public List<ClassListBean> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<ClassListBean> class_list) {
            this.class_list = class_list;
        }

        public static class ClassListBean {
            /**
             * sc_id : 1
             * sc_name : 珠宝/首饰
             * sc_bail : 0
             * sc_sort : 8
             * sc_nav : [{"sc_id":"11","sc_name":"珠宝二级","sc_bail":"0","sc_sort":"0","typeid":"1","showh":"0","picurl":null}]
             */

            private int sc_id;
            private String sc_name;
            private String sc_bail;
            private int sc_sort;
            private List<ScNavBean> sc_nav;

            public int getSc_id() {
                return sc_id;
            }

            public void setSc_id(int sc_id) {
                this.sc_id = sc_id;
            }

            public String getSc_name() {
                return sc_name;
            }

            public void setSc_name(String sc_name) {
                this.sc_name = sc_name;
            }

            public String getSc_bail() {
                return sc_bail;
            }

            public void setSc_bail(String sc_bail) {
                this.sc_bail = sc_bail;
            }

            public int getSc_sort() {
                return sc_sort;
            }

            public void setSc_sort(int sc_sort) {
                this.sc_sort = sc_sort;
            }

            public List<ScNavBean> getSc_nav() {
                return sc_nav;
            }

            public void setSc_nav(List<ScNavBean> sc_nav) {
                this.sc_nav = sc_nav;
            }

            public static class ScNavBean {
                /**
                 * sc_id : 11
                 * sc_name : 珠宝二级
                 * sc_bail : 0
                 * sc_sort : 0
                 * typeid : 1
                 * showh : 0
                 * picurl : null
                 */

                private int sc_id;
                private String sc_name;
                private String sc_bail;
                private int sc_sort;
                private int typeid;
                private String showh;
                private String picurl;

                public int getSc_id() {
                    return sc_id;
                }

                public void setSc_id(int sc_id) {
                    this.sc_id = sc_id;
                }

                public String getSc_name() {
                    return sc_name;
                }

                public void setSc_name(String sc_name) {
                    this.sc_name = sc_name;
                }

                public String getSc_bail() {
                    return sc_bail;
                }

                public void setSc_bail(String sc_bail) {
                    this.sc_bail = sc_bail;
                }

                public int getSc_sort() {
                    return sc_sort;
                }

                public void setSc_sort(int sc_sort) {
                    this.sc_sort = sc_sort;
                }

                public int getTypeid() {
                    return typeid;
                }

                public void setTypeid(int typeid) {
                    this.typeid = typeid;
                }

                public String getShowh() {
                    return showh;
                }

                public void setShowh(String showh) {
                    this.showh = showh;
                }

                public String getPicurl() {
                    return picurl;
                }

                public void setPicurl(String picurl) {
                    this.picurl = picurl;
                }
            }
        }
    }
}

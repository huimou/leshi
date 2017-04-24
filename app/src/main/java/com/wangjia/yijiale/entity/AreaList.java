package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class AreaList {

    /**
     * code : 200
     * datas : {"area_list":[{"area_id":"1298","area_name":"万柏林区"},{"area_id":"1299","area_name":"古交市"},{"area_id":"1300","area_name":"娄烦县"},{"area_id":"1301","area_name":"小店区"},{"area_id":"1302","area_name":"尖草坪区"},{"area_id":"1303","area_name":"晋源区"},{"area_id":"1304","area_name":"杏花岭区"},{"area_id":"1305","area_name":"清徐县"},{"area_id":"1306","area_name":"迎泽区"},{"area_id":"1307","area_name":"阳曲县"}]}
     */

    private int code;
    private DatasBean datas;

    public static AreaList objectFromData(String str) {

        return new Gson().fromJson(str, AreaList.class);
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
         * area_id : 1298
         * area_name : 万柏林区
         */

        private List<AreaListBean> area_list;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public List<AreaListBean> getArea_list() {
            return area_list;
        }

        public void setArea_list(List<AreaListBean> area_list) {
            this.area_list = area_list;
        }

        public static class AreaListBean {
            private String area_id;
            private String area_name;

            public static AreaListBean objectFromData(String str) {

                return new Gson().fromJson(str, AreaListBean.class);
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }
        }
    }
}

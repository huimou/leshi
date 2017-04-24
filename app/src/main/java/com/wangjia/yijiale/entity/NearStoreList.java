package com.wangjia.yijiale.entity;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class NearStoreList {


    /**
     * code : 200
     * msg : 获取数据成功
     * datas : [{"avatar":"http://localhost:814/data/upload/shop//common/default_store_avatar.png","name":"jungshen的测试店铺","address":"重庆市解放碑帝都广场B塔16楼","distance":"6.2km"},{"avatar":"http://localhost:814/data/upload/shop//store/05448792822646983_sm.jpg","name":"杨树的测试店铺","address":"重庆市渝北区义学路32号","distance":"13.2km"},{"avatar":"http://localhost:814/data/upload/shop//store/05354582644316773_sm.png","name":"花花公子","address":"成都市天府三街地铁口","distance":"277.4km"}]
     */

    private int code;
    private String msg;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * avatar : http://localhost:814/data/upload/shop//common/default_store_avatar.png
         * name : jungshen的测试店铺
         * address : 重庆市解放碑帝都广场B塔16楼
         * distance : 6.2km
         */

        private String avatar;
        private String name;
        private String address;
        private String distance;
        private int store_id;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
}

package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class AddressManageList {

    /**
     * code : 200
     * datas : {"address_list":[{"address_id":"3","member_id":"8","true_name":"小王","area_id":"88","province_id":"7","city_id":"124","post_code":"123456","address":"暂无","tel_phone":"12345678911","is_default":"0","province_name":"吉林","city_name":"辽源市","area_name":"晋城市"}]}
     */

    private int code;
    private DatasBean datas;

    public static AddressManageList objectFromData(String str) {

        return new Gson().fromJson(str, AddressManageList.class);
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
         * address_id : 3
         * member_id : 8
         * true_name : 小王
         * area_id : 88
         * province_id : 7
         * city_id : 124
         * post_code : 123456
         * address : 暂无
         * tel_phone : 12345678911
         * is_default : 0
         * province_name : 吉林
         * city_name : 辽源市
         * area_name : 晋城市
         */

        private List<AddressListBean> address_list;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public List<AddressListBean> getAddress_list() {
            return address_list;
        }

        public void setAddress_list(List<AddressListBean> address_list) {
            this.address_list = address_list;
        }

        public static class AddressListBean implements Serializable {
            private String address_id;
            private String member_id;
            private String true_name;
            private String area_id;
            private String province_id;
            private String city_id;
            private String post_code;
            private String address;
            private String tel_phone;
            private String is_default;
            private String province_name;
            private String city_name;
            private String area_name;

            public static AddressListBean objectFromData(String str) {

                return new Gson().fromJson(str, AddressListBean.class);
            }

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getTrue_name() {
                return true_name;
            }

            public void setTrue_name(String true_name) {
                this.true_name = true_name;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getPost_code() {
                return post_code;
            }

            public void setPost_code(String post_code) {
                this.post_code = post_code;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTel_phone() {
                return tel_phone;
            }

            public void setTel_phone(String tel_phone) {
                this.tel_phone = tel_phone;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public String getProvince_name() {
                return province_name;
            }

            public void setProvince_name(String province_name) {
                this.province_name = province_name;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
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

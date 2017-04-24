package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

/**
 * Created by kevin on 2016/10/26
 */

public class Login {

    /**
     * code : 1
     * msg : 登录成功
     * datas : {"member_id":"8","token":"a5b091fb0f33c45f0f1b8a39ce12d61f","member_name":"17760332009","member_idcard":null,"member_truename":null,"member_avatar":"data/upload/shop/avatar/icon_default.png","member_sex":null,"member_age":null,"member_birthday":null,"member_passwd":"e10adc3949ba59abbe56e057f20f883e","member_paypwd":null,"member_email":"","member_email_bind":"0","member_mobile":"17760332009","member_mobile_bind":"0","member_qq":null,"member_ww":null,"member_login_num":"1","member_time":"1484027288","member_login_time":"1484027288","member_old_login_time":"1484027288","member_login_ip":"192.168.0.107","member_old_login_ip":"192.168.0.107","member_qqopenid":null,"member_qqinfo":null,"member_sinaopenid":null,"member_sinainfo":null,"weixin_unionid":null,"weixin_info":null,"member_points":"20","available_predeposit":"0.00","freeze_predeposit":"0.00","available_rc_balance":"0.00","freeze_rc_balance":"0.00","inform_allow":"1","is_buy":"1","is_allowtalk":"1","member_state":"1","member_snsvisitnum":"0","member_areaid":null,"member_cityid":null,"member_provinceid":null,"member_areainfo":null,"member_privacy":null,"member_exppoints":"0","invite_one":"0","invite_two":"0","invite_three":"0","inviter_id":null}
     */

    private int code;
    private String msg;
    /**
     * member_id : 8
     * token : a5b091fb0f33c45f0f1b8a39ce12d61f
     * member_name : 17760332009
     * member_idcard : null
     * member_truename : null
     * member_avatar : data/upload/shop/avatar/icon_default.png
     * member_sex : null
     * member_age : null
     * member_birthday : null
     * member_passwd : e10adc3949ba59abbe56e057f20f883e
     * member_paypwd : null
     * member_email :
     * member_email_bind : 0
     * member_mobile : 17760332009
     * member_mobile_bind : 0
     * member_qq : null
     * member_ww : null
     * member_login_num : 1
     * member_time : 1484027288
     * member_login_time : 1484027288
     * member_old_login_time : 1484027288
     * member_login_ip : 192.168.0.107
     * member_old_login_ip : 192.168.0.107
     * member_qqopenid : null
     * member_qqinfo : null
     * member_sinaopenid : null
     * member_sinainfo : null
     * weixin_unionid : null
     * weixin_info : null
     * member_points : 20
     * available_predeposit : 0.00
     * freeze_predeposit : 0.00
     * available_rc_balance : 0.00
     * freeze_rc_balance : 0.00
     * inform_allow : 1
     * is_buy : 1
     * is_allowtalk : 1
     * member_state : 1
     * member_snsvisitnum : 0
     * member_areaid : null
     * member_cityid : null
     * member_provinceid : null
     * member_areainfo : null
     * member_privacy : null
     * member_exppoints : 0
     * invite_one : 0
     * invite_two : 0
     * invite_three : 0
     * inviter_id : null
     */

    private DatasBean datas;

    public static Login objectFromData(String str) {

        return new Gson().fromJson(str, Login.class);
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
         * token : 4af6f1ac021b47b0578980b0a489ca9c
         * member_id : 10
         * member_avatar : null
         * member_name : 15922965490
         * member_sex : null
         * member_age : null
         * member_truename : null
         * member_idcard : null
         */

        private String token;
        private String member_id;

        public String getMember_avatar() {
            return member_avatar;
        }

        private String member_avatar;
        private String member_name;
        private int member_sex;
        private int member_age;
        private String member_truename;
        private String member_idcard;
        private String member_nickname;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getMember_nickname() {
            return member_nickname;
        }

        public void setMember_nickname(String member_nickname) {
            this.member_nickname = member_nickname;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public int getMember_sex() {
            return member_sex;
        }

        public void setMember_sex(int member_sex) {
            this.member_sex = member_sex;
        }

        public int getMember_age() {
            return member_age;
        }

        public void setMember_age(int member_age) {
            this.member_age = member_age;
        }

        public String getMember_truename() {
            return member_truename;
        }

        public void setMember_truename(String member_truename) {
            this.member_truename = member_truename;
        }

        public String getMember_idcard() {
            return member_idcard;
        }

        public void setMember_idcard(String member_idcard) {
            this.member_idcard = member_idcard;
        }
    }
}

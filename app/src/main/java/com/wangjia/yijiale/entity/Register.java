package com.wangjia.yijiale.entity;

import com.google.gson.Gson;

/**
 * Created by kevin on 2016/10/26
 */

public class Register {

    /**
     * code : 1
     * msg : 注册成功
     * datas :    "member_name":"15922965490",
     "token":"3d02dd8afd763b78b68c9543aab990fe"
     */

    private int code;
    private String msg;
    /**
     * member_mobile : 187829668521
     * member_id : 51
     */

    private DatasBean datas;

    public static Register objectFromData(String str) {

        return new Gson().fromJson(str, Register.class);
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
        private String member_name;
        private String token;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

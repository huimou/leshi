package com.wangjia.yijiale.entity;

/**
 * Created by kevin on 2016/10/26
 */

public class UpdatePasswordBean {


    /**
     * code : 1
     * msg : 找回成功
     * datas : true
     */

    private int code;
    private String msg;
    private boolean datas;

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

    public boolean isDatas() {
        return datas;
    }

    public void setDatas(boolean datas) {
        this.datas = datas;
    }
}

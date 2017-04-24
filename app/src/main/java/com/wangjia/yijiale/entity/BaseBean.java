package com.wangjia.yijiale.entity;

/**
 * Created by kevin on 2016/10/26
 */

public class BaseBean {


    /**
     * code : 200
     * msg : 收藏店铺成功！
     * datas : null
     */

    private int code;
    private String msg;
    private Object datas;

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

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }
}

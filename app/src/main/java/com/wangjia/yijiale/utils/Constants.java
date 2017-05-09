package com.wangjia.yijiale.utils;

/**
 * Created by Samson on 2016/5/26.
 * 全局变量存放
 */
public class Constants {

    //UI
    public static String FROM = "from";
    public static int LOGINUI = 0;
    public static int REGISTUI = 1;
    public static int MAIN = 2;
    //

    public static String UID = "uid";//uid为null或者""为未登录
    public static String TOKEN = "token";//token
    public static String NiChen = "nichen";//token
    public static String Sex = "sex";//token
    public static String Age = "age";//token
    public static String RealName = "realname";//token
    public static String CardNum = "cardnnum";//token
    /**
     * //用户ID
     */
    public static String MEMBER_ID = "member_id";
    /**
     * 用户手机号
     */
    public static String MEMBER_MOBILE = "member_mobile";
    /**
     * 用户密码
     */
    public static String MEMBER_PASSWORD = "member_password";
    /**
     * 电话号
     */
    public static String MEMBER_NAME = "member_name";
    /**
     * 用户头像
     */
    public static String MEMBER_AVATAR = "member_avatar";
    public static String STORE_ID = "store_id";

    /**
     * String: ""
     */
    public static final String STR_NULL = "";
    /**
     * 手机验证码时间
     */
    public static int GET_CODE_TIME = 60000;
    /**
     * 返回成功
     */
    public static final int RESPONSE_SUCCESS = 200;
    /**
     * 返回失败
     */
    public static final int RESPONSE_FAILED = 500;
    /**
     * TOKEN 验证失败
     */
    public static final int TOKEN_RESPONSE_FAILED = 501;

}

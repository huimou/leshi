package com.wangjia.yijiale.utils;

public class HttpUtils {
    /**
     * 拼接图片地址
     *
     * @param url
     * @return
     */
    public static String getImageUrl(String url) {
        if (StringFunction.isNotNull(url)) {
            if (url.contains("http://")) {
                return url;
            } else {
                return Config.URI + url;
            }
        }
        return "";
    }

}

package com.wangjia.yijiale;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Vibrator;

import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.SPUtils;

import java.util.ArrayList;

/**
 * Created by kevin on 2016/10/26
 */
public class YiApplication extends Application {

    public static YiApplication yiApplication = null;
    private static YiApplication instance;
    public static Context mContext;
    private static ArrayList<Activity> activities = new ArrayList<Activity>();
    private Vibrator mVibrator;
    private String token = "";
    private static String latitude = "";
    private static String longitude = "";
    /***
     * 存储临时文件名称
     */
    public static final String TEMP_FILE_NAME = "xiaomiImage";

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 JPush
        instance = this;

    }

    public String getToken() {
        return (String) SPUtils.get(getApplicationContext(), Constants.TOKEN, "");
    }


    public static Context getContext() {
        return yiApplication;
    }

    // 构造方法
    // 实例化一次
    public synchronized static YiApplication getInstance() {
        if (null == instance) {
            instance = new YiApplication();
        }
        return instance;
    }

    // 添加Activity到容器中
    public static void addActivity(Activity activity) {
        if (activities != null && activities.size() > 0) {
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
        } else {
            activities.add(activity);
        }
    }

    // 遍历所有Activity并finish
    public static void exit() {
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    // 遍历所有Activity并finish
    public static void exitActivity() {
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                activity.finish();
            }
        }
    }

    public static String getLatitude() {
        return latitude;
    }

    public static void setLatitude(String latitude) {
        YiApplication.latitude = latitude;
    }

    public static String getLongitude() {
        return longitude;
    }

    public static void setLongitude(String longitude) {
        YiApplication.longitude = longitude;
    }
}

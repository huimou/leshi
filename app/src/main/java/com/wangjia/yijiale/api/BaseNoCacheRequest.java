package com.wangjia.yijiale.api;


import com.wangjia.yijiale.utils.Config;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kevin on 2016/10/26
 */
public class BaseNoCacheRequest {

    private BaseNoCacheRequest() {
    }

    private static BaseApi baseApi = null;
    private static final Object monitor = new Object();

    public static BaseApi getBaseApi() {
        synchronized (monitor) {
            if (baseApi == null) {
                baseApi = new Retrofit.Builder()
                        .baseUrl(Config.URI)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(BaseApi.class);
            }
            return baseApi;
        }
    }
}

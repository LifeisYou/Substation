package com.xczn.substation.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author zhangxiao
 * @Date 2018/8/3 0003
 * @Comment
 */
public class RetrofitUtils {
    private static String baseUrl = null;
    private static final int TIME_OUT=4;
    private Retrofit retrofit;

    /**
     * 构造函数私有化
     * 并在构造函数中进行retrofit的初始化
     * 由于retrofit底层的实现是通过okhttp实现的，所以可以通过okHttp来设置一些连接参数
     * 如超时等
     */
    private RetrofitUtils() {
        OkHttpClient client=new OkHttpClient();
        client.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class signalInstance {
        static final RetrofitUtils instance = new RetrofitUtils();
    }

    public static Retrofit getRetrofit(){
        return signalInstance.instance.retrofit;
    }
}

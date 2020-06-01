package com.example.ourblog.model.network.networkutil;


import com.example.ourblog.MainActivity;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.ourblog.model.network.UrlStaticQuality.BASE_URL;

/**
 * @author singsong
 * Retrofit单例工具类
 * 内部自己创建一个retrofit实例，调用里面的方法传入一个接口class就可以返回一个接口实例
 * 需要提供名为BASE_URL的静态String常量
 * 调用示例：
 * FoodPreviewApiService foodPreviewApiService = RetrofitManager.getInstance().createRs(FoodPreviewApiService.class);
 */
public class RetrofitManager {

    private static RetrofitManager retrofitManager;
    private Retrofit mRetrofit;

    /**
     * 构造器私有，全局retrofit单例
     */
    private RetrofitManager(){
        //添加cookie相关的拦截器
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new ReceivedCookiesInterceptor(MainActivity.getContext()));
        httpClientBuilder.addInterceptor(new AddCookiesInterceptor(MainActivity.getContext()));


        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    /**
     * 获取单例的静态方法。synchronized是上锁的意思，就是多个线程只能同时被使用一次
     * @return retrofit单例
     */
    public static synchronized RetrofitManager getInstance(){
        if (retrofitManager == null){
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }


    /**
     * 利用泛型传入接口class返回接口实例
     * @param ser retrofit 网络请求接口类
     * @param <T> 返回数据实体类
     * @return 构造请求接口对象
     */
    public <T> T createRs(Class<T> ser){
        return mRetrofit.create(ser);
    }
}

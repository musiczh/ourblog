package com.example.ourblog.model.network.networkutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ourblog.MainActivity;
import com.example.ourblog.model.network.netbean.LoginData;

import java.io.IOException;
import java.util.ResourceBundle;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author singsong
 * 保持更新本地的token，记住登录
 */
public class KeepTokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        SharedPreferences sharedPreferences = MainActivity.getContext().
                getSharedPreferences("Login", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");

        Request.Builder requestBuilder = chain.request().newBuilder();
        if (!token.isEmpty()){
            requestBuilder.addHeader("token",token);
        }

        Log.d("huan",requestBuilder.build().url().toString());

        Response response = chain.proceed(requestBuilder.build());

        token = response.request().header("token");
        if (token != null){
            sharedPreferences.edit().putString("token",token).apply();
        }

        Log.d("huan",response.request().url().toString());
        Log.d("huan",response.body().toString());
        return response;
    }
}

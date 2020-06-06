package com.example.ourblog.model.network.apiservice;

import com.example.ourblog.model.network.netbean.BaseBean;
import com.example.ourblog.model.network.netbean.LoginData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import static com.example.ourblog.model.network.UrlStaticQuality.*;

/**
 * @author singsong
 * 登录注册相关接口
 */
public interface LoginApiService {

    /**
     * 注册请求接口
     * @param username  用户名
     * @param password  密码
     * @param email     邮箱
     * @param phone     电话号码
     * @param address   地址
     * @return  返回retrofit的Call对象
     */
    @POST(REGISTER_URL)
    Call<BaseBean<String>> register(
            @Query("username")String username,
            @Query("password")String password,
            @Query("email")String email,
            @Query("phone")String phone,
            @Query("address")String address);

    /**
     * 登录请求接口
     * @param username  用户名
     * @param password  密码
     * @return  返回retrofit的call对象
     */
    @POST(LOGIN_URL)
    Call<BaseBean<LoginData>> login(
            @Query("username")String username,
            @Query("password")String password
    );

    /**
     * 修改密码请求接口
     * @param username  用户名
     * @param oldPassword   旧密码
     * @param newPassword   新密码
     * @return  返回retrofit的call对象
     */
    @POST(ALTER_PASSWORD_URL)
    Call<BaseBean<String>> alterPassword(
            @Query("username")String username,
            @Query("old_pwd")String oldPassword,
            @Query("new_pad")String newPassword
    );
}

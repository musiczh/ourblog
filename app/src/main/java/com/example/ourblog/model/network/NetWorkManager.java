package com.example.ourblog.model.network;

import com.example.ourblog.model.entity.CallBack;
import com.example.ourblog.model.network.netbean.RegisterMsg;
import com.example.ourblog.model.network.apiservice.LoginApiService;
import com.example.ourblog.model.network.netbean.BaseBean;
import com.example.ourblog.model.network.netbean.LoginData;
import com.example.ourblog.model.network.networkutil.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author singsong
 * 网络层管理类
 */
public class NetWorkManager {
    private static NetWorkManager mNetWorkManager;

    private NetWorkManager(){ }

    public static synchronized NetWorkManager getInstance(){
        if (mNetWorkManager == null){
            mNetWorkManager = new NetWorkManager();
        }
        return mNetWorkManager;
    }

    public void register(RegisterMsg registerMsg, final CallBack<String> callBack){
        RetrofitManager.getInstance().createRs(LoginApiService.class)
                .register(
                        registerMsg.getUsername(),
                        registerMsg.getPassword(),
                        registerMsg.getEmail(),
                        registerMsg.getPhone(),
                        registerMsg.getAddress()
                ).enqueue(new Callback<BaseBean<String>>() {
            @Override
            public void onResponse(Call<BaseBean<String>> call, Response<BaseBean<String>> response) {
                if (response.body()!=null){
                    BaseBean<String> bean = response.body();
                    if (bean.getCode()==1){
                        callBack.onSucceed("注册成功");
                    }else{
                        callBack.onFailed(bean.getMsg());
                    }
                }else {
                    callBack.onFailed("body==null");
                }
            }

            @Override
            public void onFailure(Call<BaseBean<String>> call, Throwable t) {
                callBack.onFailed("onFailure");
            }
        });
    }


    public void login(String username,String password,final CallBack<LoginData> callBack){
        RetrofitManager.getInstance().createRs(LoginApiService.class)
                .login(username,password)
                .enqueue(new Callback<BaseBean<LoginData>>() {
                    @Override
                    public void onResponse(Call<BaseBean<LoginData>> call, Response<BaseBean<LoginData>> response) {
                        if (response.body()!=null){
                            BaseBean<LoginData> bean = response.body();
                            if (bean.getCode()==1){
                                callBack.onSucceed(bean.getData());
                            }else{
                                callBack.onFailed(bean.getMsg());
                            }

                        }else {
                            callBack.onFailed("body==null");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean<LoginData>> call, Throwable t) {
                        t.printStackTrace();
                        callBack.onFailed("onFailure");
                    }
                });
    }

    public void alterPassword(String username,
                              String oldPassword,
                              String newPassword,
                              final CallBack<String> callBack) {
        RetrofitManager.getInstance().createRs(LoginApiService.class)
                .alterPassword(username, oldPassword, newPassword)
                .enqueue(new Callback<BaseBean<String>>() {
                    @Override
                    public void onResponse(Call<BaseBean<String>> call, Response<BaseBean<String>> response) {
                        if (response.body()!=null){
                            BaseBean<String> bean = response.body();
                            if (bean.getCode()==1){
                                callBack.onSucceed(bean.getMsg());
                            }else{
                                callBack.onFailed(bean.getMsg());
                            }
                        }else {
                            callBack.onFailed("body==null");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean<String>> call, Throwable t) {
                            callBack.onFailed("onFailure");
                    }
                });

    }

}

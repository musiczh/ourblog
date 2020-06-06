package com.example.ourblog.model;

import com.example.ourblog.model.dao.DaoManager;
import com.example.ourblog.model.entity.CallBack;
import com.example.ourblog.model.network.netbean.RegisterMsg;
import com.example.ourblog.model.network.NetWorkManager;
import com.example.ourblog.model.network.netbean.LoginData;

/**
 * @author singsong
 * 仓库单例类。全局数据保存者。所有view都在这里获取数据。
 */
public class Reposity {
    private static Reposity mReposity;
    private NetWorkManager netWorkManager;
    private DaoManager daoManager;

    private Reposity(){
        netWorkManager = NetWorkManager.getInstance();
        daoManager = DaoManager.getInstance();
    }

    public static synchronized Reposity getInstance(){
        if (mReposity == null){
            mReposity = new Reposity();
        }
        return mReposity;
    }

    public void register(RegisterMsg registerMsg, CallBack<String> callBack){
        netWorkManager.register(registerMsg, callBack);
    }

    public void login(String username, String password, CallBack<LoginData> callBack){
        netWorkManager.login(username, password, callBack);
    }

    public void alterPassword(String username,String oldPassword,String newPassword,CallBack<String> callBack){
        netWorkManager.alterPassword(username, oldPassword, newPassword, callBack);
    }


}

package com.example.ourblog.model.network;

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



}

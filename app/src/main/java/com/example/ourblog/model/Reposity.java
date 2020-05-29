package com.example.ourblog.model;

import com.example.ourblog.dao.DaoManager;
import com.example.ourblog.network.NetWorkManager;

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

}

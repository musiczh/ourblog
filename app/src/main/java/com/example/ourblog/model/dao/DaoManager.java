package com.example.ourblog.model.dao;

/**
 * @author singsong
 * dao层管理类
 */
public class DaoManager {
    private static DaoManager mDaoManager;

    private DaoManager(){}

    public static synchronized DaoManager getInstance() {
        if (mDaoManager == null){
            mDaoManager = new DaoManager();
        }
        return mDaoManager;
    }
}

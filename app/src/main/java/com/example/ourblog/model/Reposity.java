package com.example.ourblog.model;

import com.example.ourblog.CallBack;
import com.example.ourblog.model.bean.GankArticleItem;
import com.example.ourblog.model.bean.WanArticleItem;
import com.example.ourblog.model.dao.DaoManager;
import com.example.ourblog.model.network.NetWorkManager;

import java.util.List;

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

    //如果是初始化就先从数据库拿数据再访问网络，如果不是则直接访问网路
    public void getWanArticleItem(final String id, boolean isInit, final CallBack<List<WanArticleItem>> callBack){
        //如果是初始化就访问数据库获取数据
        if(isInit){
            daoManager.getWanArtiItem(callBack);
        }
        netWorkManager.getWanArticle(id,new CallBack<List<WanArticleItem>>(){

            @Override
            public void success(List<WanArticleItem> items) {
                callBack.success(items);
                if("0".equals(id)){
                    daoManager.insertWaArtiItem(items);
                }
            }

            @Override
            public void failed(String msg) {
                callBack.failed(msg);
            }
        });
    }

    public void getGranArticleItem(final String id, boolean isInit, final CallBack<List<GankArticleItem>> callBack){
        //如果是初始化就访问数据库获取数据
        if(isInit){
            daoManager.getGrankArtiItem(callBack);
        }
        netWorkManager.getGrankArticle(id,new CallBack<List<GankArticleItem>>(){

            @Override
            public void success(List<GankArticleItem> items) {
                callBack.success(items);
                if("1".equals(id)){
                    daoManager.insertGraArtiItem(items);
                }
            }

            @Override
            public void failed(String msg) {
                callBack.failed(msg);
            }
        });
    }




}

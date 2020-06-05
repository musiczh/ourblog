package com.example.ourblog.model;

import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;

import com.example.ourblog.model.dao.DaoManager;
import com.example.ourblog.model.network.NetWorkManager;
import com.example.ourblog.viewmodel.BaseViewModel;

import java.util.ArrayList;
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
    public LiveData<List<WanArticleItem>> getWanArticleItem(final String id, boolean isInit, final BaseViewModel.CallBack<List<WanArticleItem>> callBack){
        LiveData<List<WanArticleItem>> listLiveData=null;
        //如果是初始化就访问数据库获取数据
        if(isInit){
            listLiveData=daoManager.getWanArtiItem();
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
        return listLiveData;
    }

    public interface CallBack<T> {

        void success(T t);
        void failed(String msg);
    }

}

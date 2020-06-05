package com.example.ourblog.model.dao;

import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;

import com.example.ourblog.MainActivity;
import com.example.ourblog.model.WanArticleItem;
import com.example.ourblog.model.dao.room.AppDatabase;
import com.example.ourblog.model.dao.room.WanArticleItemDao;
import com.example.ourblog.util.ThreadPoolManager;
import com.example.ourblog.viewmodel.BaseViewModel;

import java.util.List;

/**
 * @author singsong
 * dao层管理类
 */
public class DaoManager {
    private static DaoManager mDaoManager;
    private static AppDatabase mDb;
    private ThreadPoolManager mThreadPoolManager;

    private DaoManager(){
        mThreadPoolManager=ThreadPoolManager.getInstance();
    }

    public static synchronized DaoManager getInstance() {
        if (mDaoManager == null){
            mDaoManager = new DaoManager();
            mDb=AppDatabase.getInstance(MainActivity.getContext());
        }
        return mDaoManager;
    }

    public void insertWaArtiItem(final List<WanArticleItem> items){

        final WanArticleItemDao itemDao=mDb.wanArticleItemDao();
        mThreadPoolManager.addDefaultTask(new Runnable() {
            @Override
            public void run() {
                itemDao.deleteAll();
                itemDao.insertAll(items.toArray(new WanArticleItem[0]));
            }
        });

    }

    public void getWanArtiItem(BaseViewModel.CallBack<List<WanArticleItem>> callBack){
        mThreadPoolManager.addDefaultTask(new Runnable() {
            @Override
            public void run() {
                callBack.success(mDb.wanArticleItemDao().getItemAll());
            }
        });
    }


}

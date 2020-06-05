package com.example.ourblog.viewmodel.mainviewmodel;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ourblog.CallBack;
import com.example.ourblog.MainActivity;
import com.example.ourblog.model.bean.GankArticleItem;
import com.example.ourblog.model.Reposity;

import java.util.List;

public class GankViewModel extends ViewModel {

    private MutableLiveData<List<GankArticleItem>> mLiveItems = new MutableLiveData<>();
    private  MutableLiveData<String> mRecycleBottom;
    private int page=1;
    private CallBack<List<GankArticleItem>> mRefreashCallBack;
    Reposity mReposity;

    public GankViewModel(){
        init();
    }

    public void init(){
        mReposity= Reposity.getInstance();
        mRecycleBottom=new MutableLiveData<>();
        mRefreashCallBack=new CallBack<List<GankArticleItem>>(){

            @Override
            public void success(List<GankArticleItem> items) {
                List<GankArticleItem> list=mLiveItems.getValue();
                if(list!=null){
                    list.clear();
                    list.addAll(items);
                    mLiveItems.postValue(list);
                }else{
                    mLiveItems.postValue(items);
                }

            }

            @Override
            public void failed(String msg) {
                Toast.makeText(MainActivity.getContext(),msg,Toast.LENGTH_SHORT).show();
                mRecycleBottom.postValue(msg);
                page--;
            }
        };
        mReposity.getGranArticleItem(String.valueOf(page++),true,mRefreashCallBack);
    }


    public void getMoreItem() {
        mReposity.getGranArticleItem(String.valueOf(page++), false, new CallBack<List<GankArticleItem>>() {
            @Override
            public void success(List<GankArticleItem> items) {
                List<GankArticleItem> list=mLiveItems.getValue();
                if(list!=null){
                    list.addAll(items);
                    mLiveItems.setValue(list);
                }
            }

            @Override
            public void failed(String msg) {
                Toast.makeText(MainActivity.getContext(),msg,Toast.LENGTH_SHORT).show();
                mRecycleBottom.postValue(msg);
                page--;
            }
        });
    }


    public void refreshItems() {
        page=0;
        mReposity.getGranArticleItem(String.valueOf(page++),false,mRefreashCallBack);
    }


    public LiveData<List<GankArticleItem>> getItems() {
        return mLiveItems;
    }


    public LiveData<String> getRecycleBottomString() {
        return mRecycleBottom;
    }
    // TODO: Implement the ViewModel
}
package com.example.ourblog.viewmodel.mainviewmodel;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ourblog.CallBack;
import com.example.ourblog.MainActivity;
import com.example.ourblog.model.Reposity;
import com.example.ourblog.model.bean.WanArticleItem;

import java.util.List;

public class WanViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<WanArticleItem>> mLiveItems = new MutableLiveData<>();
    private  MutableLiveData<String> mRecycleBottom;
    private int page=0;
    private CallBack<List<WanArticleItem>> mRefreashCallBack;
    Reposity mReposity;



    public WanViewModel(){
        init();
    }

    private void init(){
        mReposity= Reposity.getInstance();
        mRecycleBottom=new MutableLiveData<>();
        mRefreashCallBack=new CallBack<List<WanArticleItem>>(){

            @Override
            public void success(List<WanArticleItem> items) {
                List<WanArticleItem> list=mLiveItems.getValue();
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
        mReposity.getWanArticleItem(String.valueOf(page++),true,mRefreashCallBack);
    }


    public void getMoreItem(){
        mReposity.getWanArticleItem(String.valueOf(page++), false, new CallBack<List<WanArticleItem>>() {
            @Override
            public void success(List<WanArticleItem> items) {
                List<WanArticleItem> list=mLiveItems.getValue();
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


    public void refreshItems(){
        page=0;
        mReposity.getWanArticleItem(String.valueOf(page++),false,mRefreashCallBack);
    }


    public LiveData<List<WanArticleItem>> getItems() { return mLiveItems; }

    public LiveData<String> getRecycleBottomString(){ return mRecycleBottom;}
}
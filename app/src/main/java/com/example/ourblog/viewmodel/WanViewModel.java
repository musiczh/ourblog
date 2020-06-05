package com.example.ourblog.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ourblog.MainActivity;
import com.example.ourblog.model.Reposity;
import com.example.ourblog.model.WanArticleItem;

import java.util.List;

public class WanViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private LiveData<List<WanArticleItem>> mLiveItems;
    private  MutableLiveData<String> mRecycleBottom;
    private int page=0;
    private BaseViewModel.CallBack<List<WanArticleItem>> mRefreashCallBack;
    Reposity mReposity;



    public WanViewModel(){
        init();
    }

    private void init(){
        mReposity= Reposity.getInstance();
        mRecycleBottom=new MutableLiveData<>();
        mRecycleBottom.setValue("正在努力加载...");
        mRefreashCallBack=new BaseViewModel.CallBack<List<WanArticleItem>>(){

            @Override
            public void success(List<WanArticleItem> items) {
                List<WanArticleItem> list=mLiveItems.getValue();
                if(list!=null){
                    list.clear();
                    list.addAll(items);
                }
            }

            @Override
            public void failed(String msg) {
                Toast.makeText(MainActivity.getContext(),msg,Toast.LENGTH_SHORT).show();
                mRecycleBottom.setValue(msg);
            }
        };
        mLiveItems= mReposity.getWanArticleItem(String.valueOf(page++),true,mRefreashCallBack);
    }

    public void getMoreItem(){
        mReposity.getWanArticleItem(String.valueOf(page++), false, new BaseViewModel.CallBack<List<WanArticleItem>>() {
            @Override
            public void success(List<WanArticleItem> items) {
                List<WanArticleItem> list=mLiveItems.getValue();
                if(list!=null){
                    list.addAll(items);
                }
            }

            @Override
            public void failed(String msg) {
                Toast.makeText(MainActivity.getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<List<WanArticleItem>> getItems() { return mLiveItems; }
    public LiveData<String> getRecycleBottomString(){ return mRecycleBottom;}
}
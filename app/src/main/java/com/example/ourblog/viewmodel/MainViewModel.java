package com.example.ourblog.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ourblog.MainActivity;
import com.example.ourblog.model.Reposity;
import com.example.ourblog.model.WanArticleItem;

import java.util.List;

public class MainViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel
    /**private LiveData<List<WanArticleItem>> mLiveItems;
    private int page=0;
    private BaseViewModel.CallBack<List<WanArticleItem>> mRefreashCallBack;
    Reposity mReposity;
    public MainViewModel(){
        init();
    }

    private void init(){
        mReposity= Reposity.getInstance();
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
            }
        };
        mLiveItems= mReposity.getWanArticleItem(String.valueOf(page++),true,mRefreashCallBack);
    }

    public LiveData<List<WanArticleItem>> getItems() {
        return mLiveItems;
    }**/
}

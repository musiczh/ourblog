package com.example.ourblog.viewmodel.mainviewmodel;


import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
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

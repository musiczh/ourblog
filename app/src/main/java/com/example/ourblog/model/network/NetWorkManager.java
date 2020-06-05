package com.example.ourblog.model.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ourblog.model.Reposity;
import com.example.ourblog.model.network.bean.WanArticleData;
import com.example.ourblog.model.WanArticleItem;
import com.example.ourblog.model.network.api.WanArticleApi;
import com.example.ourblog.model.network.networkutil.RetrofitManager;
import com.example.ourblog.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author singsong
 * 网络层管理类
 */
public class NetWorkManager {
    private static NetWorkManager mNetWorkManager;
    private static final String SERVER_ERROR="服务器出现问题";
    private static final String INTERNET_ERROR="网络链接失败";
    private String id;


    private NetWorkManager(){ }

    public static synchronized NetWorkManager getInstance(){
        if (mNetWorkManager == null){
            mNetWorkManager = new NetWorkManager();
        }
        return mNetWorkManager;
    }

    public void getWanArticle(String id, final Reposity.CallBack<List<WanArticleItem>> callBack){
        RetrofitManager manager=RetrofitManager.getInstance();
        WanArticleApi api=manager.createRs(WanArticleApi.class);
        Call<WanArticleData> items=api.getArticle(id);
        items.enqueue(new Callback<WanArticleData>() {
            @Override
            public void onResponse(@NonNull Call<WanArticleData> call, @NonNull Response<WanArticleData> response) {
                WanArticleData data=response.body();
                if(data==null){
                    callBack.failed(SERVER_ERROR);
                }else if(data.getErrorCode()!=0){
                    callBack.failed(data.getErrorMsg());
                }else{
                    WanArticleData.DataBean dataBean=data.getData();
                    List<WanArticleData.DataBean.DatasBean> datasBeanList=dataBean.getDatas();
                    List<WanArticleItem> items=new ArrayList<>();
                    WanArticleData.DataBean.DatasBean temp;
                    for(int i=0;i<datasBeanList.size();i++){
                        temp=datasBeanList.get(i);
                        items.add(new WanArticleItem(temp.getLink(),temp.getNiceShareDate(),temp.getTitle(),temp.getShareUser()));
                    }

                    callBack.success(items);
                }


            }

            @Override
            public void onFailure(@NonNull Call<WanArticleData> call, @NonNull Throwable t) {
                callBack.failed(INTERNET_ERROR);
            }
        });
    }



}

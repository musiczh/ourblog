package com.example.ourblog.model.network;

import androidx.annotation.NonNull;

import com.example.ourblog.CallBack;
import com.example.ourblog.model.bean.GankArticleItem;
import com.example.ourblog.model.bean.WanArticleItem;
import com.example.ourblog.model.network.api.GankApiService;
import com.example.ourblog.model.network.databean.GankArticleData;
import com.example.ourblog.model.network.databean.WanArticleData;
import com.example.ourblog.model.network.api.WanApiService;
import com.example.ourblog.model.network.networkutil.RetrofitManager;

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

    public void getWanArticle(String id, final CallBack<List<WanArticleItem>> callBack){
        RetrofitManager manager=RetrofitManager.getInstance();
        WanApiService api=manager.createRs(WanApiService.class);
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

    public void getGrankArticle(String id, final CallBack<List<GankArticleItem>> callBack){
        RetrofitManager manager=RetrofitManager.getInstance();
        GankApiService api=manager.createRs(GankApiService.class);
        Call<GankArticleData> items=api.getArticle(id);
        items.enqueue(new Callback<GankArticleData>() {
            @Override
            public void onResponse(@NonNull Call<GankArticleData> call, @NonNull Response<GankArticleData> response) {
                GankArticleData data=response.body();
                if(data==null){
                    callBack.failed(SERVER_ERROR);
                }else if(data.getStatus()!=100){
                    callBack.failed("错误代码：100");
                }else{
                    List<GankArticleData.DataBean> datasBeanList=data.getData();
                    List<GankArticleItem> items=new ArrayList<>();
                    GankArticleData.DataBean temp;
                    String imgLink=null;
                    for(int i=0;i<datasBeanList.size();i++){
                        temp=datasBeanList.get(i);
                        if(temp.getImages().size()>0){
                            imgLink=temp.getImages().get(0);
                        }
                        items.add(new GankArticleItem("https://gank.io/post/"+temp.get_id(),temp.getCreatedAt()
                                ,temp.getTitle(),temp.getAuthor(),imgLink,temp.getDesc()));
                    }
                    callBack.success(items);
                }


            }

            @Override
            public void onFailure(@NonNull Call<GankArticleData> call, @NonNull Throwable t) {
                callBack.failed(INTERNET_ERROR);
            }
        });
    }



}

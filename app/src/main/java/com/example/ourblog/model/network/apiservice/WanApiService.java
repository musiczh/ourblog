package com.example.ourblog.model.network.apiservice;

import com.example.ourblog.model.network.databean.WanArticleData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanApiService {
    @GET("https://www.wanandroid.com/article/list/{id}/json")
    Call<WanArticleData> getArticle(@Path("id") String id);

}

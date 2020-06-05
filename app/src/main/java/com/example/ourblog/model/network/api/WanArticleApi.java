package com.example.ourblog.model.network.api;

import com.example.ourblog.model.network.bean.WanArticleData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanArticleApi{
    @GET("article/list/{id}/json")
    Call<WanArticleData> getArticle(@Path("id") String id);
}

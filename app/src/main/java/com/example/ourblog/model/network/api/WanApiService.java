package com.example.ourblog.model.network.api;

import com.example.ourblog.model.network.databean.WanArticleData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanApiService {
    @GET("article/list/{id}/json")
    Call<WanArticleData> getArticle(@Path("id") String id);

}

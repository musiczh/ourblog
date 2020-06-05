package com.example.ourblog.model.network.api;

import com.example.ourblog.model.network.databean.GankArticleData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApiService {

    @GET("https://gank.io/api/v2/data/category/GanHuo/type/Android/page/{id}/count/20")
    Call<GankArticleData> getArticle(@Path("id") String id);
}

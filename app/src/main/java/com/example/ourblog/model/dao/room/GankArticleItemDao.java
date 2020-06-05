package com.example.ourblog.model.dao.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ourblog.model.bean.GankArticleItem;

import java.util.List;

@Dao
public interface GankArticleItemDao {

    @Query("DELETE FROM grank_article_item_table")
    void deleteAll();

    @Insert
    void insertAll(GankArticleItem... items);

    @Query("select * from grank_article_item_table")
    List<GankArticleItem> getItemAll();
}

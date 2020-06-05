package com.example.ourblog.model.dao.room;

import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ourblog.model.WanArticleItem;

import java.util.List;

@Dao
public interface WanArticleItemDao {

    @Query("DELETE FROM wan_article_item_table")
    void deleteAll();

    @Insert
    void insertAll(WanArticleItem... items);

    @Query("select * from wan_article_item_table")
    List<WanArticleItem> getItemAll();

}

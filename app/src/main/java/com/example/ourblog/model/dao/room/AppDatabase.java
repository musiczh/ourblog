package com.example.ourblog.model.dao.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ourblog.model.WanArticleItem;

@Database(entities = {WanArticleItem.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database").build();
                }
            }
        }
        return instance;
    }

    public abstract WanArticleItemDao wanArticleItemDao();
}

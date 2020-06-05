package com.example.ourblog.model.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "wan_article_item_table")
public class WanArticleItem {
    /**
     * link : https://juejin.im/post/5ecf642b6fb9a04805262605
     * niceShareDate : 2020-05-29 11:41
     * shareUser : 逮虾户
     * title : Kotlin拓展函数的真身
     */

    @PrimaryKey
    @NonNull
    private String link;
    private String niceShareDate;
    private String shareUser;
    private String title;


    public WanArticleItem(@NotNull String link, String niceShareDate, String title, String shareUser) {
        this.link = link;
        this.niceShareDate = niceShareDate;
        this.title = title;
        this.shareUser = shareUser;

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceShareDate() {
        return niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
        this.niceShareDate = niceShareDate;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

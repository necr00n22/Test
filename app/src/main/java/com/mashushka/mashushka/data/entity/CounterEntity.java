package com.mashushka.mashushka.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.mashushka.mashushka.data.Comment;

import java.util.List;

/**
 * Created by Михаил on 12.03.2018.
 */
@Entity(tableName = "counters")
public class CounterEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "createDate")
    long createDate;
    @ColumnInfo(name = "endDate")
    long endDate;
    @ColumnInfo(name = "comments")
    List<Comment> comments;


    public CounterEntity (String title, long createDate, long endDate) {
        this.title = title;
        this.createDate = createDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}

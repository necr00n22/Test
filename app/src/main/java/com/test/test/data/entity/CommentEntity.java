package com.test.test.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Mikhail Li (Jiub) on 12.07.2018.
 */
@Entity(tableName = "comments")
public class CommentEntity {

    public final static int TYPE_COMMENT = 0;
    public final static int TYPE_ADD_COMMENT = 1;
    public final static int TYPE_SHOW_ALL_COMMENTS = 2;

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "counterId")
    long counterId;
    @ColumnInfo(name = "createdDate")
    Date createdDate;
    @ColumnInfo(name = "counterCreatedDate")
    Date counterCreatedDate;
    @ColumnInfo(name = "text")
    String text;

    private int type = TYPE_COMMENT;

    public CommentEntity(long counterId, Date createdDate, Date counterCreatedDate, String text) {
        this.counterId = counterId;
        this.createdDate = createdDate;
        this.counterCreatedDate = counterCreatedDate;
        this.text = text;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public Date getCounterCreatedDate() {
        return counterCreatedDate;
    }

    public void setCounterCreatedDate(Date counterCreatedDate) {
        this.counterCreatedDate = counterCreatedDate;
    }

    public long getCounterId() {
        return counterId;
    }

    public void setCounterId(long counterId) {
        this.counterId = counterId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.mashushka.mashushka.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Михаил on 12.03.2018.
 */
@Entity(tableName = "counters")
public class CounterEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "startDate")
    long startDate;
    @ColumnInfo(name = "createDate")
    long createDate;
    @ColumnInfo(name = "counter")
    long counter;

    public CounterEntity (String title, long startDate, long createDate, long counter) {
        this.title = title;
        this.startDate = startDate;
        this.createDate = createDate;
        this.counter = counter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }
}

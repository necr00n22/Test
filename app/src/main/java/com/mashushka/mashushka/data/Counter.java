package com.mashushka.mashushka.data;

import android.content.ContentValues;

import com.mashushka.mashushka.database.DB;

/**
 * Created by Mikhail Li (Jiub) on 03.02.2018.
 */

public class Counter {

    private String title;
    private long modifiedDate;
    private long createDate;
    private long counter;

    public Counter (String title, long startDate, long createDate, long counter) {

        this.title = title;
        this.modifiedDate = startDate;
        this.createDate = createDate;
        this.counter = counter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public ContentValues createContentValues(){
        ContentValues cv = new ContentValues();

        cv.put(DB.COUNTER_TITLE, title);
        cv.put(DB.COUNTER_CREATE_DATE, createDate);
        cv.put(DB.COUNTER_MODIFIED_DATE, modifiedDate);
        cv.put(DB.COUNTER_COUNTER, counter);

        return cv;
    }
}

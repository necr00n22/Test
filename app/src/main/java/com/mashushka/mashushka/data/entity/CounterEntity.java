package com.mashushka.mashushka.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.mashushka.mashushka.data.Block;

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
    @ColumnInfo(name = "blocks")
    List<Block> blocks;


    public CounterEntity (String title, long createDate, long endDate, List<Block> blocks) {
        this.title = title;
        this.createDate = createDate;
        this.endDate = endDate;
        this.blocks = blocks;
    }

    public int getId() {
        return id;
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

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }


}

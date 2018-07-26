package com.mashushka.mashushka.data;

import android.arch.persistence.room.TypeConverter;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.TypedValue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

/**
 * Created by Михаил on 12.03.2018.
 */

public class Converters {

    @TypeConverter
    public static long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date fromLong(Long time) {
        return time == null ? null : new Date(time);
    }

    @TypeConverter
    public static List<Comment> stringToCommentsList(String data) {
        return data == null ? null : new Gson().fromJson(data, new TypeToken<List<Comment>>() {}.getType());
    }

    @TypeConverter
    public static String commentsListToString(List<Comment> data) {
        return data == null ? null : new Gson().toJson(data);
    }

    @TypeConverter
    public static List<Block> fromString(String data) {
        return data == null ? null : new Gson().fromJson(data, new TypeToken<List<Block>>() {}.getType());
    }

    @TypeConverter
    public static String fromBlockList(List<Block> data) {
        return data == null ? null : new Gson().toJson(data);
    }

}

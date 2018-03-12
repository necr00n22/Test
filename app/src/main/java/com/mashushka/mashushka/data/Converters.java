package com.mashushka.mashushka.data;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Михаил on 12.03.2018.
 */

public class Converters {

    @TypeConverter
    public static long toLong(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long time) {
        return time == null ? null : new Date(time);
    }

}

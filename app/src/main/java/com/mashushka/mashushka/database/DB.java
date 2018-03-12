package com.mashushka.mashushka.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mashushka.mashushka.data.Counter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mikhail Li (Jiub) on 04.02.2018.
 */

public class DB {

    public static final String TABLE_COUNTERS = "t_counters";

    public static final String COUNTER_TITLE = "title";
    public static final String COUNTER_CREATE_DATE = "create_date";
    public static final String COUNTER_MODIFIED_DATE = "modified_date";
    public static final String COUNTER_COUNTER = "counter";



    private Context mContext;
    private DbHelper mDbHelper;
    SQLiteDatabase db;

    public DB (Context context) {
        mContext = context;
    }

    private void open() {
        mDbHelper = new DbHelper(mContext);
        db = mDbHelper.getWritableDatabase();
    }


    private void close() {
        if (mDbHelper!=null) mDbHelper.close();
    }

    public void insertData(ContentValues cv, String tableName) {
        open();
        db.insert(tableName, null, cv);
        close();
    }

    public List<Counter> getCounters() {
        List<Counter> data = new ArrayList<>();
        open();
        Cursor settingsCursor = db.rawQuery("SELECT * FROM " + DB.TABLE_COUNTERS, null);
        if (settingsCursor.moveToFirst()) {
            while (!settingsCursor.isAfterLast()) {
                String title = settingsCursor.getString(settingsCursor.getColumnIndex(COUNTER_TITLE));
                Long createDate = settingsCursor.getLong(settingsCursor.getColumnIndex(COUNTER_CREATE_DATE));
                Long modifiedDate = settingsCursor.getLong(settingsCursor.getColumnIndex(COUNTER_MODIFIED_DATE));
                int counter = settingsCursor.getInt(settingsCursor.getColumnIndex(COUNTER_COUNTER));
                data.add(new Counter(title, modifiedDate, createDate, counter));
                settingsCursor.moveToNext();
            }
        }
        settingsCursor.close();
        close();
        return data;
    }
}

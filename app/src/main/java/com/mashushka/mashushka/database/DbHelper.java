package com.mashushka.mashushka.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.mashushka.mashushka.Constants.DB_NAME;
import static com.mashushka.mashushka.Constants.DB_VERSION;

/**
 * Created by Mikhail Li (Jiub) on 04.02.2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_COUNTERS = "CREATE TABLE IF NOT EXISTS t_counters (" +
            "id INTEGER PRIMATY KEY," +
            "title TEXT," +
            "create_date TEXT," +
            "modified_date TEXT," +
            "counter INTEGER" +
            ")";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_COUNTERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

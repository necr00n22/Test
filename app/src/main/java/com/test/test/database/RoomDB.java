package com.test.test.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.test.test.data.Converters;
import com.test.test.data.dao.CommentDao;
import com.test.test.data.dao.CounterDao;
import com.test.test.data.entity.CommentEntity;
import com.test.test.data.entity.CounterEntity;

/**
 * Created by Михаил on 12.03.2018.
 */

@Database(entities = {CounterEntity.class, CommentEntity.class}, version = 7)
@TypeConverters(Converters.class)
public abstract class RoomDB extends RoomDatabase {

//    private static RoomDB sInstance;

    public static final String DATABASE_NAME = "basic-sample-db";

    public abstract CounterDao counterDao();
    public abstract CommentDao commentDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static RoomDB getInstance(final Context context, final AppExecutors executors) {
        RoomDB sInstance;
            synchronized (RoomDB.class) {
                sInstance = buildDatabase(context.getApplicationContext(), executors);
                sInstance.updateDatabaseCreated(context.getApplicationContext());
            }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static RoomDB buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, RoomDB.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            RoomDB database = RoomDB.getInstance(appContext, executors);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                })
                .fallbackToDestructiveMigration()
                .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
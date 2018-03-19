package com.mashushka.mashushka.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;

import com.mashushka.mashushka.data.entity.CounterEntity;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by Михаил on 12.03.2018.
 */

public class DataRepository {

    private static DataRepository sInstance;

    private final RoomDB mDatabase;
    private MediatorLiveData<List<CounterEntity>> mObservableProducts;

    private DataRepository(final RoomDB database) {
        mDatabase = database;
        mObservableProducts = new MediatorLiveData<>();

        mObservableProducts.addSource(mDatabase.counterDao().getCounters(),
                productEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableProducts.postValue(productEntities);
                    }
                });
    }

    public static DataRepository getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(RoomDB.getInstance(context, new AppExecutors()));
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<CounterEntity>> getCounters() {
        return mObservableProducts;
    }

    public LiveData<CounterEntity> getCounterById(long id) {
        return mDatabase.counterDao().getCounterById(id);
    }

    public void insertCounters(List<CounterEntity> entities) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mDatabase.counterDao().insertCounters(entities);
        });
    }
    public void insertSingleCounter(CounterEntity entity) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mDatabase.runInTransaction(() -> mDatabase.counterDao().insertSingleCounter(entity));

        });
    }
}
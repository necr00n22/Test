package com.test.test.database;

import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;

import com.test.test.data.entity.CounterEntity;

import java.util.List;

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





}
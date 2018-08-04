package com.test.test.database;

import android.arch.lifecycle.LiveData;

import com.test.test.data.entity.CounterEntity;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by Михаил on 04.08.2018.
 */

public class CounterRepository {

    private final RoomDB mDatabase;

    public CounterRepository(final RoomDB database) {
        mDatabase = database;
    }

    public LiveData<List<CounterEntity>> getCounters() {
        return mDatabase.counterDao().getCounters();
    }

    public LiveData<CounterEntity> getCounterById(long id) {
        return mDatabase.counterDao().getCounterById(id);
    }

    public void updateSingleCounter(CounterEntity entity) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mDatabase.counterDao().updateCounter(entity);
        });
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

    public void deleteSingleCounter(long id) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mDatabase.counterDao().deleteCounter(id);
        });
    }

}

package com.test.test.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.test.test.data.entity.CounterEntity;

import java.util.List;

/**
 * Created by Михаил on 12.03.2018.
 */
@Dao
public interface CounterDao {

    @Query("SELECT * FROM counters ORDER BY id DESC")
    LiveData<List<CounterEntity>> getCounters();

    @Query("SELECT * FROM counters WHERE id = :counterId")
    LiveData<CounterEntity> getCounterById(long counterId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCounters(List<CounterEntity> entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSingleCounter(CounterEntity entity);

    @Update
    void updateCounter(CounterEntity counter);

    @Query("DELETE FROM counters WHERE id = :counterId")
    void deleteCounter(long counterId);

}

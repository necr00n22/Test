package com.test.test.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.test.test.data.entity.CounterEntity;
import com.test.test.database.CounterRepository;
import com.test.test.di.App;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Михаил on 12.03.2018.
 */

public class CountersListViewModel extends AndroidViewModel {

    @Inject
    CounterRepository repository;

    private LiveData<List<CounterEntity>> counters;

    public CountersListViewModel(@NonNull Application application) {
        super(application);
        App.getComponent().inject(this);
    }

    public LiveData<List<CounterEntity>> getCounters() {
        return counters;
    }

    public void initObservers() {
        counters = repository.getCounters();
    }
}

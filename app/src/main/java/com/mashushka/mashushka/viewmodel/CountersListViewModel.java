package com.mashushka.mashushka.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.database.DataRepository;

import java.util.List;

/**
 * Created by Михаил on 12.03.2018.
 */

public class CountersListViewModel extends AndroidViewModel {

    private LiveData<List<CounterEntity>> counters;
    private Context mContext;

    public CountersListViewModel(@NonNull Application application) {
        super(application);
        this.mContext = application;
    }

    public LiveData<List<CounterEntity>> getCounters() {
        return counters;
    }

    public void initObservers() {
        counters = DataRepository.getInstance(mContext).getCounters();
    }
}

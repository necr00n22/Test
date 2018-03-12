package com.mashushka.mashushka.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.Nullable;

import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.database.DataRepository;

import java.util.List;

/**
 * Created by Михаил on 12.03.2018.
 */

public class CounterViewModel extends ViewModel {

    private LiveData<List<CounterEntity>> counters;
    public LiveData<List<CounterEntity>> getCounters() {
        return counters;
    }

    public void initObservers(Context context, LifecycleOwner owner) {
        counters = DataRepository.getInstance(context).getCounters();
    }
}

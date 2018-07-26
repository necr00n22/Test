package com.mashushka.mashushka.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.database.DataRepository;

/**
 * Created by Mikhail Li (Jiub) on 19.03.2018.
 */

public class CounterViewModel extends AndroidViewModel {

    private Context mContext;

    public CounterViewModel(@NonNull Application application ) {
        super(application);
        this.mContext = application;
    }

    public LiveData<CounterEntity> getCounterById(long id) {
        return DataRepository.getInstance(mContext).getCounterById(id);
    }

}

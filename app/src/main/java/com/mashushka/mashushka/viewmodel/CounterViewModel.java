package com.mashushka.mashushka.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mashushka.mashushka.data.entity.CommentEntity;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.database.DataRepository;

import java.util.List;

/**
 * Created by Mikhail Li (Jiub) on 19.03.2018.
 */

public class CounterViewModel extends AndroidViewModel {

    private Context mContext;
    private long counterId;

    public CounterViewModel(@NonNull Application application, long counterId) {
        super(application);
        this.mContext = application;
        this.counterId = counterId;
    }

    public LiveData<CounterEntity> getCounter() {
        return DataRepository.getInstance(mContext).getCounterById(counterId);
    }

    public LiveData<List<CommentEntity>> getComments() {
        return DataRepository.getInstance(mContext).getCommentsById(counterId);
    }

}

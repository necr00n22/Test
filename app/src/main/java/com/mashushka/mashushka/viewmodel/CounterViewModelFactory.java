package com.mashushka.mashushka.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

/**
 * Created by Mikhail Li (Jiub) on 27.07.2018.
 */

public class CounterViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final long counterId;

    public CounterViewModelFactory(Application application, long counterId) {
        this.application = application;
        this.counterId = counterId;
    }

    @Override
    public CounterViewModel create(Class modelClass) {
        return new CounterViewModel(application, counterId);
    }
}

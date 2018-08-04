package com.test.test.viewmodel.factory;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.test.test.viewmodel.CommentsListViewModel;

/**
 * Created by Mikhail Li (Jiub) on 27.07.2018.
 */

public class CommentsListViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final long counterId;

    public CommentsListViewModelFactory(Application application, long counterId) {
        this.application = application;
        this.counterId = counterId;
    }

    @Override
    public CommentsListViewModel create(Class modelClass) {
        return new CommentsListViewModel(application, counterId);
    }
}

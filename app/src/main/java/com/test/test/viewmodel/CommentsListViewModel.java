package com.test.test.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.test.test.data.entity.CommentEntity;
import com.test.test.database.repositories.CommentRepository;
import com.test.test.di.App;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Михаил on 03.08.2018.
 */

public class CommentsListViewModel extends AndroidViewModel {

    @Inject
    CommentRepository repository;

    private long counterId;

    public CommentsListViewModel(@NonNull Application application, long counterId) {
        super(application);
        App.getComponent().inject(this);
        this.counterId = counterId;
    }

    public LiveData<List<CommentEntity>> getComments() {
        return repository.getCommentsById(counterId);
    }
}

package com.test.test.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.test.test.data.entity.CommentEntity;
import com.test.test.data.entity.CounterEntity;
import com.test.test.database.CommentRepository;
import com.test.test.database.CounterRepository;
import com.test.test.di.App;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Mikhail Li (Jiub) on 19.03.2018.
 */

public class CounterViewModel extends AndroidViewModel {

    @Inject
    CounterRepository counterRepository;
    @Inject
    CommentRepository commentRepository;

    private long counterId;

    private MutableLiveData<Boolean> isShowFullComments = new MutableLiveData<>();

    public CounterViewModel(@NonNull Application application, long counterId) {
        super(application);
        App.getComponent().inject(this);
        this.counterId = counterId;
        isShowFullComments.setValue(false);

    }

    public LiveData<CounterEntity> getCounter() {
        return counterRepository.getCounterById(counterId);
    }

    public void setShowFullComments(boolean showFullComments) {
        isShowFullComments.postValue(showFullComments);
    }

    public LiveData<List<CommentEntity>> getComments() {
        return Transformations.switchMap(isShowFullComments, showFullComments -> showFullComments ? commentRepository.getCommentsById(counterId) : commentRepository.getLastCommentsById(counterId));
    }

    public void deleteCounter() {
        counterRepository.deleteSingleCounter(counterId);
    }

    public void addComment(CommentEntity comment) {
        commentRepository.insertSingleComment(comment);
    }
}

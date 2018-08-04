package com.test.test.database;

import android.arch.lifecycle.LiveData;

import com.test.test.data.entity.CommentEntity;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by Михаил on 04.08.2018.
 */

public class CommentRepository {

    private final RoomDB mDatabase;

    public CommentRepository(final RoomDB database) {
        mDatabase = database;
    }

    public LiveData<List<CommentEntity>> getCommentsById(long counterId) {
        return mDatabase.commentDao().getCommentsByCounterId(counterId);
    }

    public LiveData<List<CommentEntity>> getLastCommentsById(long counterId) {
        return mDatabase.commentDao().getLastCommentsByCounterId(counterId);
    }

    public void insertSingleComment(CommentEntity entity) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mDatabase.runInTransaction(() -> mDatabase.commentDao().insertSingleComment(entity));

        });
    }

}

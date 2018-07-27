package com.mashushka.mashushka.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mashushka.mashushka.data.entity.CommentEntity;
import com.mashushka.mashushka.data.entity.CounterEntity;

import java.util.List;

/**
 * Created by Михаил on 12.03.2018.
 */
@Dao
public interface CommentDao {

    @Query("SELECT * FROM comments WHERE counterId = :counterId ORDER BY id DESC")
    LiveData<List<CommentEntity>> getCommentsByCounterId(long counterId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComments(List<CommentEntity> entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSingleComment(CommentEntity entity);

    @Query("DELETE FROM comments WHERE id = :commentId")
    void deleteComments(long commentId);

}

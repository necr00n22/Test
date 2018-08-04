package com.test.test.ui;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;

import com.test.test.data.entity.CommentEntity;

import java.util.List;

/**
 * Created by Mikhail Li (Jiub) on 27.07.2018.
 */

public interface CommentsProvider {
    LiveData<List<CommentEntity>> getComments();
    void addComment(CommentEntity comment);
    void showComment(long commentId);
    void showAllComments();

    LifecycleOwner getLyfecycleOwner();
}

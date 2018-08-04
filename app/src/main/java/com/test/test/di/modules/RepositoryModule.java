package com.test.test.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.test.test.database.AppExecutors;
import com.test.test.database.CommentRepository;
import com.test.test.database.CounterRepository;
import com.test.test.database.RoomDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Михаил on 04.08.2018.
 */
@Module
public class RepositoryModule {

    @Provides
    @NonNull
    @Singleton
    public RoomDB provideRoomDb(Context context, AppExecutors executors) {
        return RoomDB.getInstance(context, executors);
    }

    @Provides
    @NonNull
    @Singleton
    public CommentRepository provideCommentRepository(RoomDB roomDB) {
        return new CommentRepository(roomDB);
    }

    @Provides
    @NonNull
    @Singleton
    public CounterRepository provideCounterRepository(RoomDB roomDB) {
        return new CounterRepository(roomDB);
    }

}

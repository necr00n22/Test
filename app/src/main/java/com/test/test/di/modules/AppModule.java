package com.test.test.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.test.test.database.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Михаил on 04.08.2018.
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(@NonNull Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return  mContext;
    }

    @Provides
    @Singleton
    AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

}

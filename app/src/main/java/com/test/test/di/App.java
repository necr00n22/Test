package com.test.test.di;

import android.app.Application;

import com.test.test.di.modules.AppModule;
import com.test.test.di.modules.RepositoryModule;

/**
 * Created by Михаил on 04.08.2018.
 */

public class App extends Application {
    private static AppComponent appComponent;

    public static AppComponent getComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .repositoryModule(new RepositoryModule())
                .build();
    }
}

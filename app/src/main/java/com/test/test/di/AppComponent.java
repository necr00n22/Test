package com.test.test.di;

import com.test.test.di.modules.AppModule;
import com.test.test.di.modules.RepositoryModule;
import com.test.test.ui.dialogs.CreateCounterBottomSheetDialog;
import com.test.test.viewmodel.CommentsListViewModel;
import com.test.test.viewmodel.CounterViewModel;
import com.test.test.viewmodel.CountersListViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Михаил on 04.08.2018.
 */
@Component(modules = {AppModule.class, RepositoryModule.class})
@Singleton
public interface AppComponent {

    void inject(CounterViewModel counterViewModel);
    void inject(CountersListViewModel counterListViewModel);
    void inject(CommentsListViewModel commentsListViewModel);
    void inject(CreateCounterBottomSheetDialog createCounterBottomSheetDialog);
}

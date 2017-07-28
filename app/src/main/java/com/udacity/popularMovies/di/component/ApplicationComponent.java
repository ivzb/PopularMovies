package com.udacity.popularMovies.di.component;

import android.app.Application;
import android.content.Context;

import com.udacity.popularMovies.MvpApp;
import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.di.ApplicationContext;
import com.udacity.popularMovies.di.module.ApplicationModule;
import com.udacity.popularMovies.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
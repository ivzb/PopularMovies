package com.udacity.popularMovies.di.component;

import com.udacity.popularMovies.di.PerActivity;
import com.udacity.popularMovies.di.module.ActivityModule;
import com.udacity.popularMovies.ui.main.MainActivity;
import com.udacity.popularMovies.ui.main.sort.SortDialog;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(SortDialog dialog);
}

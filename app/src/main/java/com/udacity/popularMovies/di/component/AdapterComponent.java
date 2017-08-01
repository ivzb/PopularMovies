package com.udacity.popularMovies.di.component;

import com.udacity.popularMovies.di.PerActivity;
import com.udacity.popularMovies.di.module.ActivityModule;
import com.udacity.popularMovies.di.module.AdapterModule;
import com.udacity.popularMovies.ui.main.adapters.MoviesAdapter;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = AdapterModule.class)
public interface AdapterComponent {

    void inject(MoviesAdapter adapter);
}

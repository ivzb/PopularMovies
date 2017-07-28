package com.udacity.popularMovies.ui.main;

import com.udacity.popularMovies.di.PerActivity;
import com.udacity.popularMovies.ui.base.MvpPresenter;

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onViewInitialized();
}

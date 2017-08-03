package com.udacity.popularMovies.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.udacity.popularMovies.di.ActivityContext;
import com.udacity.popularMovies.di.PerActivity;
import com.udacity.popularMovies.ui.details.DetailsMvpPresenter;
import com.udacity.popularMovies.ui.details.DetailsMvpView;
import com.udacity.popularMovies.ui.details.DetailsPresenter;
import com.udacity.popularMovies.ui.details.DetailsViewModel;
import com.udacity.popularMovies.ui.main.MainMvpPresenter;
import com.udacity.popularMovies.ui.main.MainMvpView;
import com.udacity.popularMovies.ui.main.MainPresenter;
import com.udacity.popularMovies.ui.main.MainViewModel;
import com.udacity.popularMovies.utils.rx.AppSchedulerProvider;
import com.udacity.popularMovies.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainViewModel provideMainViewModel() {
        return new MainViewModel();
    }

    @Provides
    @PerActivity
    DetailsMvpPresenter<DetailsMvpView> provideDetailsPresenter(
            DetailsPresenter<DetailsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailsViewModel provideDetailsViewModel() {
        return new DetailsViewModel();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    GridLayoutManager provideGridLayoutManager(AppCompatActivity activity) {
        return new GridLayoutManager(activity, 2);
    }
}

package com.udacity.popularMovies.ui.details;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.ui.base.BasePresenter;
import com.udacity.popularMovies.ui.base.MvpView;
import com.udacity.popularMovies.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailsPresenter<V extends MvpView> extends BasePresenter<V> implements
        DetailsMvpPresenter<V> {

    private static final String TAG = "DetailsPresenter";

    @Inject
    public DetailsPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
package com.udacity.popularMovies.ui.main;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.ui.base.BasePresenter;
import com.udacity.popularMovies.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewInitialized() {
        getCompositeDisposable()
            .add(
                getDataManager()
                    .getPopularMoviesApiCall()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<MoviesResponse>() {
                        @Override
                        public void accept(MoviesResponse moviesResponse) throws Exception {
                            if (!isViewAttached()) {
                                return;
                            }

                            if (moviesResponse != null) {
                                getMvpView().refreshMovies(moviesResponse.getResults());
                            }
                        }
                    })
            );
    }

    @Override
    public void onMovieClicked(MoviesResponse.Movie movie) {
        // todo
    }
}

package com.udacity.popularMovies.ui.main;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.ui.base.BasePresenter;
import com.udacity.popularMovies.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Observable;
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
    public void onViewInitialized(SortBy sortBy) {
        this.onSortClicked(sortBy);
    }

    @Override
    public void onSortClicked(SortBy sortBy) {
        DataManager dataManager = getDataManager();
        Observable<MoviesResponse> observable;

        if (sortBy == null) sortBy = SortBy.MostPopular;

        switch (sortBy) {
            case TopRated:
                observable = dataManager.getTopRatedMoviesApiCall();
                break;
            case MostPopular:
                observable = dataManager.getPopularMoviesApiCall();
                break;
            default:
                throw new IllegalArgumentException(sortBy + " is not implemented");
        }

        getMvpView().showLoading();

        getCompositeDisposable()
            .add(observable
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MoviesResponse>() {
                    @Override
                    public void accept(MoviesResponse moviesResponse) throws Exception {
                        if (isViewUnattached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        if (moviesResponse == null) return;

                        getMvpView().refreshMovies(moviesResponse.getResults());
                    }
                })
            );
    }


    @Override
    public void onMovieClicked(MoviesResponse.Movie movie) {
        getMvpView().openDetailsActivity(movie);
    }
}

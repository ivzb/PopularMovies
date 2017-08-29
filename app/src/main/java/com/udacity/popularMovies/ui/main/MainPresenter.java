/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.udacity.popularMovies.ui.main;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.data.network.model.Movie;
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
    MainPresenter(DataManager dataManager,
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
        Observable<MoviesResponse> observable;

        if (sortBy == null) sortBy = SortBy.MostPopular;

        switch (sortBy) {
            case TopRated:
                observable = getDataManager().getTopRatedMoviesApiCall();
                break;
            case MostPopular:
                observable = getDataManager().getPopularMoviesApiCall();
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
    public void onMovieClicked(Movie movie) {
        getMvpView().openDetailsActivity(movie);
    }
}

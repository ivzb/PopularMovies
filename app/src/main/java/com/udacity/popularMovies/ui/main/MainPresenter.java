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
import com.udacity.popularMovies.data.callbacks.GetCallback;
import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V>, GetCallback<MoviesResponse> {

    private static final String TAG = "MainPresenter";

    @Inject
    MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onViewInitialized(SortBy sortBy) {
        this.onSortClicked(sortBy);
    }

    @Override
    public void onSortClicked(SortBy sortBy) {
        if (sortBy == null) sortBy = SortBy.MostPopular;

        switch (sortBy) {
            case TopRated:
                getDataManager().getTopRatedMoviesApiCall(this);
                break;
            case MostPopular:
                getDataManager().getPopularMoviesApiCall(this);
                break;
            default:
                throw new IllegalArgumentException(sortBy + " is not implemented");
        }

        getMvpView().showLoading();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        getMvpView().openDetailsActivity(movie);
    }

    @Override
    public void onSuccess(MoviesResponse moviesResponse) {
        if (isViewUnattached()) return;

        getMvpView().hideLoading();

        if (moviesResponse == null) return;

        getMvpView().refreshMovies(moviesResponse.getResults());
    }

    @Override
    public void onFailure(String message) {
        if (isViewUnattached()) return;

        getMvpView().hideLoading();

        getMvpView().onError(message);
    }
}
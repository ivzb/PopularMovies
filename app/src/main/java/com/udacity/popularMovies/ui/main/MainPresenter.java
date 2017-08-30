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

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.data.callbacks.GetCallback;
import com.udacity.popularMovies.data.db.DbContract;
import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.di.ApplicationContext;
import com.udacity.popularMovies.ui.base.BasePresenter;
import com.udacity.popularMovies.utils.CursorUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements
        MainMvpPresenter<V>,
        GetCallback<MoviesResponse>,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int FAVORITE_MOVIES_LOADER = 1;

    private LoaderManager mLoaderManager;
    private Context mContext;

    @Inject
    MainPresenter(@ApplicationContext Context context, DataManager dataManager) {
        super(dataManager);

        mContext = context;
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        FragmentActivity fa = (FragmentActivity)mvpView;
        mLoaderManager = fa.getSupportLoaderManager();
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
                destroyLoader();
                getDataManager().getTopRatedMoviesApiCall(this);
                break;
            case MostPopular:
                destroyLoader();
                getDataManager().getPopularMoviesApiCall(this);
                break;
            case Favorite:
                if (mLoaderManager.getLoader(FAVORITE_MOVIES_LOADER) == null) {
                    mLoaderManager.initLoader(FAVORITE_MOVIES_LOADER, null, this);
                    return;
                }

                mLoaderManager.restartLoader(FAVORITE_MOVIES_LOADER, null, this);
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
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle) {
        switch (loaderId) {
            case FAVORITE_MOVIES_LOADER:
                return new CursorLoader(mContext,
                        DbContract.MovieEntry.CONTENT_URI,
                        null,null,null,null);
            default:
                throw new RuntimeException("Loader Not Implemented: " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<Movie> movies = new ArrayList<>();

        while (data.moveToNext()) {
            Movie currentMovie = CursorUtils.from(data);
            movies.add(currentMovie);
        }

        if (isViewUnattached()) return;

        getMvpView().hideLoading();
        getMvpView().refreshMovies(movies);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        getMvpView().refreshMovies(null);
    }

    private void destroyLoader() {
        if (mLoaderManager.getLoader(FAVORITE_MOVIES_LOADER) != null) {
            mLoaderManager.destroyLoader(FAVORITE_MOVIES_LOADER);
        }
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
        getMvpView().refreshMovies(null);
        getMvpView().onError(message);
    }
}
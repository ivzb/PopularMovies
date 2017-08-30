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

package com.udacity.popularMovies.ui.details;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.data.callbacks.GetCallback;
import com.udacity.popularMovies.data.db.DbContract;
import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.ReviewsResponse;
import com.udacity.popularMovies.data.network.model.VideosResponse;
import com.udacity.popularMovies.di.ApplicationContext;
import com.udacity.popularMovies.ui.base.BasePresenter;
import com.udacity.popularMovies.utils.ContentValuesUtils;

import javax.inject.Inject;

import static com.udacity.popularMovies.data.db.DbContract.MovieEntry.buildMovieUriWithId;

public class DetailsPresenter<V extends DetailsMvpView> extends BasePresenter<V> implements
        DetailsMvpPresenter<V>,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CURRENT_MOVIE_LOADER_ID = 0;

    private LoaderManager mLoaderManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mMovieId;

    @Inject
    DetailsPresenter(@ApplicationContext Context context, DataManager dataManager) {
        super(dataManager);

        mContext = context;
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        FragmentActivity fa = (FragmentActivity)mvpView;
        mContentResolver = fa.getContentResolver();
        mLoaderManager = fa.getSupportLoaderManager();

        mLoaderManager.initLoader(CURRENT_MOVIE_LOADER_ID, null, this);
    }

    @Override
    public void onResume() {
        mLoaderManager.restartLoader(CURRENT_MOVIE_LOADER_ID, null, this);
    }

    @Override
    public void setMovieId(int movieId) {
        this.mMovieId = movieId;
    }

    @Override
    public void loadTrailersFor(int movieId) {
        getMvpView().showLoading();

        getDataManager().getTrailersApiCall(movieId, new GetCallback<VideosResponse>() {
            @Override
            public void onSuccess(VideosResponse videosResponse) {
                if (isViewUnattached()) return;

                getMvpView().hideLoading();

                if (videosResponse == null) return;

                getMvpView().loadTrailers(videosResponse.getResults());
            }

            @Override
            public void onFailure(String message) {
                if (isViewUnattached()) return;

                getMvpView().hideLoading();
                getMvpView().onError(message);
            }
        });
    }

    @Override
    public void loadReviewsFor(int movieId) {
        getMvpView().showLoading();

        getDataManager().getReviewsApiCall(movieId, new GetCallback<ReviewsResponse>() {
            @Override
            public void onSuccess(ReviewsResponse reviewsResponse) {
                if (isViewUnattached()) return;

                getMvpView().hideLoading();

                if (reviewsResponse == null) return;

                getMvpView().loadReviews(reviewsResponse.getResults());
            }

            @Override
            public void onFailure(String message) {
                if (isViewUnattached()) return;

                getMvpView().hideLoading();
                getMvpView().onError(message);
            }
        });
    }

    @Override
    public void onTrailerClicked(String key) {
        try {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
            getMvpView().playTrailer(appIntent);
        } catch (ActivityNotFoundException ex) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + key));
            getMvpView().playTrailer(webIntent);
        }
    }

    @Override
    public void favoriteMovie(Movie movie) {
        ContentValues values = ContentValuesUtils.from(movie);
        mContentResolver.insert(DbContract.MovieEntry.CONTENT_URI, values);
    }

    @Override
    public void unfavoriteMovie(Movie movie) {
        String id = String.valueOf(movie.getId());
        Uri uriToDelete = DbContract.MovieEntry.CONTENT_URI.buildUpon().appendPath(id).build();
        mContentResolver.delete(uriToDelete, null, null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle) {
        switch (loaderId) {
            case CURRENT_MOVIE_LOADER_ID:
                Uri movieQueryUri = buildMovieUriWithId(mMovieId);

                return new CursorLoader(mContext,
                        movieQueryUri,
                        null,null,null,null);

            default:
                throw new RuntimeException("Loader Not Implemented: " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (isViewUnattached()) return;

        boolean isFavorite = data.moveToFirst();
        getMvpView().setFavoriteMovie(isFavorite);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (isViewUnattached()) return;

        getMvpView().setFavoriteMovie(false);
    }
}
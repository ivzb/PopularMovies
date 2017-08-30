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

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.data.callbacks.GetCallback;
import com.udacity.popularMovies.data.db.DbContract;
import com.udacity.popularMovies.data.db.MoviesLoaderProvider;
import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.VideosResponse;
import com.udacity.popularMovies.di.ApplicationContext;
import com.udacity.popularMovies.ui.base.BasePresenter;
import com.udacity.popularMovies.utils.ContentValuesUtils;

import javax.inject.Inject;

public class DetailsPresenter<V extends DetailsMvpView> extends BasePresenter<V> implements
        DetailsMvpPresenter<V>,
        GetCallback<VideosResponse> {

    private static final String TAG = "DetailsPresenter";
    private static final int MOVIES_LOADER_ID = 1;

    private MoviesLoaderProvider mLoaderProvider;
    private LoaderManager mLoaderManager;
//    @Inject
    private ContentResolver mContentResolver;

    @Inject
    DetailsPresenter(DataManager dataManager, MoviesLoaderProvider loaderProvider) {
        super(dataManager);

        mLoaderProvider = loaderProvider;
//        mContentResolver = contentResolver;
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        FragmentActivity fa = (FragmentActivity)mvpView;
        mLoaderManager = fa.getSupportLoaderManager();
        mContentResolver = fa.getContentResolver();
    }

    @Override
    public void loadTrailersFor(int movieId) {
        getMvpView().showLoading();

        getDataManager().getTrailersApiCall(movieId, this);
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
}
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

package com.udacity.popularMovies.data;

import android.support.annotation.NonNull;

import com.udacity.popularMovies.data.callbacks.GetCallback;
import com.udacity.popularMovies.data.network.ApiEndPoint;
import com.udacity.popularMovies.data.network.ApiHelper;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.data.network.model.ReviewsResponse;
import com.udacity.popularMovies.data.network.model.VideosResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(ApiHelper apiHelper) {
        mApiHelper = apiHelper;
    }

    @Override
    public ApiEndPoint getApi() {
        return mApiHelper.getApi();
    }

    @Override
    public void getPopularMoviesApiCall(@NonNull final GetCallback<MoviesResponse> callback) {
        mApiHelper.getPopularMoviesApiCall(callback);
    }

    @Override
    public void getTopRatedMoviesApiCall(@NonNull final GetCallback<MoviesResponse> callback) {
        mApiHelper.getTopRatedMoviesApiCall(callback);
    }

    @Override
    public void getTrailersApiCall(int movieId, @NonNull final GetCallback<VideosResponse> callback) {
        mApiHelper.getTrailersApiCall(movieId, callback);
    }

    @Override
    public void getReviewsApiCall(int movieId, GetCallback<ReviewsResponse> callback) {
        mApiHelper.getReviewsApiCall(movieId, callback);
    }
}
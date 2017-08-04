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

import com.udacity.popularMovies.data.network.ApiEndPoint;
import com.udacity.popularMovies.data.network.ApiHelper;
import com.udacity.popularMovies.data.network.model.MoviesResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

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
    public Observable<MoviesResponse> getPopularMoviesApiCall() {
        return mApiHelper.getPopularMoviesApiCall();
    }

    @Override
    public Observable<MoviesResponse> getTopRatedMoviesApiCall() {
        return mApiHelper.getTopRatedMoviesApiCall();
    }
}

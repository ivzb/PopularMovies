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

package com.udacity.popularMovies.data.network;

import android.support.annotation.NonNull;

import com.udacity.popularMovies.data.callbacks.GetCallback;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.data.network.model.ReviewsResponse;
import com.udacity.popularMovies.data.network.model.VideosResponse;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AppApiHelper implements ApiHelper {

    private final ApiEndPoint mApi;

    public AppApiHelper(ApiEndPoint api) {
        mApi = api;
    }

    @Override
    public ApiEndPoint getApi() {
        return mApi;
    }

    @Override
    public void getPopularMoviesApiCall(@NonNull final GetCallback<MoviesResponse> callback) {
        final Call<MoviesResponse> call = mApi.getPopularMovies();

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();

                if (statusCode != 200) {
                    callback.onFailure("Error occurred. Please try again.");
                    return;
                }

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                callback.onFailure("Server could not be reached. Please try again.");
            }
        });
    }

    @Override
    public void getTopRatedMoviesApiCall(@NonNull final GetCallback<MoviesResponse> callback) {
        final Call<MoviesResponse> call = mApi.getTopRatedMovies();

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();

                if (statusCode != 200) {
                    callback.onFailure("Error occurred. Please try again.");
                    return;
                }

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                callback.onFailure("Server could not be reached. Please try again.");
            }
        });
    }

    @Override
    public void getTrailersApiCall(int movieId, @NonNull final GetCallback<VideosResponse> callback) {
        final Call<VideosResponse> call = mApi.getTrailersByMovieId(movieId);

        call.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                int statusCode = response.code();

                if (statusCode != 200) {
                    callback.onFailure("Error occurred. Please try again.");
                    return;
                }

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {
                callback.onFailure("Server could not be reached. Please try again.");
            }
        });
    }

    @Override
    public void getReviewsApiCall(int movieId, @NonNull final GetCallback<ReviewsResponse> callback) {
        final Call<ReviewsResponse> call = mApi.getReviewsByMovieId(movieId);

        call.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                int statusCode = response.code();

                if (statusCode != 200) {
                    callback.onFailure("Error occurred. Please try again.");
                    return;
                }

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ReviewsResponse> call, Throwable t) {
                callback.onFailure("Server could not be reached. Please try again.");
            }
        });
    }
}
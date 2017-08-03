package com.udacity.popularMovies.data.network;

import com.udacity.popularMovies.data.network.model.MoviesResponse;

import javax.inject.Singleton;

import io.reactivex.Observable;

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
    public Observable<MoviesResponse> getPopularMoviesApiCall() {
        return mApi.getPopularMovies();
    }

    @Override
    public Observable<MoviesResponse> getTopRatedMoviesApiCall() {
        return mApi.getTopRatedMovies();
    }
}


package com.udacity.popularMovies.data.network;

import com.udacity.popularMovies.data.network.model.MoviesResponse;

import io.reactivex.Observable;

public interface ApiHelper {

    ApiEndPoint getApi();

    Observable<MoviesResponse> getPopularMoviesApiCall();

    Observable<MoviesResponse> getTopRatedMoviesApiCall();
}

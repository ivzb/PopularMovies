package com.udacity.popularMovies.data.network;

import com.udacity.popularMovies.data.network.model.MoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiEndPoint {

    @GET("movie/popular")
    Observable<MoviesResponse> getPopularMovies();

    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRatedMovies();
}
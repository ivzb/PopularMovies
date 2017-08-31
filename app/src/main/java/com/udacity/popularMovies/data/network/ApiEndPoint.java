package com.udacity.popularMovies.data.network;

import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.data.network.model.ReviewsResponse;
import com.udacity.popularMovies.data.network.model.VideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndPoint {

    @GET("movie/{sort}")
    Call<MoviesResponse> getMovies(@Path("sort") String order);

    @GET("movie/{id}/videos")
    Call<VideosResponse> getTrailersByMovieId(@Path("id") int id);

    @GET("movie/{id}/reviews")
    Call<ReviewsResponse> getReviewsByMovieId(@Path("id") int id);
}
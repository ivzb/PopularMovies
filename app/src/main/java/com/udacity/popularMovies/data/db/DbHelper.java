package com.udacity.popularMovies.data.db;

import com.udacity.popularMovies.data.network.model.MoviesResponse;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<List<MoviesResponse.Movie>> getFavoriteMovies();

    Observable<Boolean> saveFavoriteMovie(MoviesResponse.Movie movie);
}
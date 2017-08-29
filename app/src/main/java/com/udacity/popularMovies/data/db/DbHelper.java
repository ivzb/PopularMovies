package com.udacity.popularMovies.data.db;

import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.MoviesResponse;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<List<Movie>> getFavoriteMovies();

    void saveFavoriteMovie(Movie movie);
}
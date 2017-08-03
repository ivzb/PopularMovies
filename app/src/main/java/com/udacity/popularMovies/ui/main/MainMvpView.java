package com.udacity.popularMovies.ui.main;

import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void refreshMovies(List<MoviesResponse.Movie> movies);

    void showSortDialog();

    void openDetailsActivity(MoviesResponse.Movie movie);
}
package com.udacity.popularMovies.ui.main;

import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.di.PerActivity;
import com.udacity.popularMovies.ui.base.MvpPresenter;
import com.udacity.popularMovies.ui.main.sort.SortBy;

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onViewInitialized();

    void onSortClicked(SortBy sortBy);

    void onMovieClicked(MoviesResponse.Movie movie);
}

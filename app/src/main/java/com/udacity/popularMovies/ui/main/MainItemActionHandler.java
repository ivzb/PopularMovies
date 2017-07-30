package com.udacity.popularMovies.ui.main;

import com.udacity.popularMovies.data.network.model.MoviesResponse;

/**
 * Listens to user actions from the RecyclerView items in ({@link MainActivity}) and redirects them to the
 * activity actions listener.
 */
public class MainItemActionHandler {

    private MainMvpPresenter<MainMvpView> mListener;

    public MainItemActionHandler(MainMvpPresenter<MainMvpView> listener) {
        this.mListener = listener;
    }

    /**
     * Called by the Data Binding library when the item is clicked.
     */
    public void movieClicked(MoviesResponse.Movie movie) {
        this.mListener.onMovieClicked(movie);
    }
}
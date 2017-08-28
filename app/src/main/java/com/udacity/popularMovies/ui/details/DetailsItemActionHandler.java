package com.udacity.popularMovies.ui.details;

import com.udacity.popularMovies.data.network.model.VideosResponse;
import com.udacity.popularMovies.ui.main.MainActivity;

/**
 * Listens to user actions from the RecyclerView items in ({@link MainActivity}) and redirects them to the
 * activity actions listener.
 */
public class DetailsItemActionHandler {

    private final DetailsMvpPresenter<DetailsMvpView> mListener;

    DetailsItemActionHandler(DetailsMvpPresenter<DetailsMvpView> listener) {
        this.mListener = listener;
    }

    /**
     * Called by the Data Binding library when the item is clicked.
     */
    public void trailerClicked(VideosResponse.Video trailer) {
        this.mListener.onTrailerClicked(trailer.getKey());
    }
}
package com.udacity.popularMovies.ui.details;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.udacity.popularMovies.BR;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.ui.main.MainActivity;

/**
 * Exposes the data to be used in the {@link MainActivity}.
 * <p>
 * {@link BaseObservable} implements a listener registration mechanism which is notified when a
 * property changes. This is done by assigning a {@link Bindable} annotation to the property's
 * getter method.
 */
public class DetailsViewModel extends BaseObservable {

    private MoviesResponse.Movie mMovie;

    public DetailsViewModel() {

    }

    @Bindable
    public MoviesResponse.Movie getMovie() {
        return this.mMovie;
    }

    void setMovie(MoviesResponse.Movie movie) {
        this.mMovie = movie;
        notifyPropertyChanged(BR.movie);
    }
}
/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.udacity.popularMovies.ui.details;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;

import com.udacity.popularMovies.BR;
import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.ui.details.adapters.TrailersAdapter;
import com.udacity.popularMovies.ui.main.MainActivity;
import com.udacity.popularMovies.ui.main.adapters.MoviesAdapter;

/**
 * Exposes the data to be used in the {@link MainActivity}.
 * <p>
 * {@link BaseObservable} implements a listener registration mechanism which is notified when a
 * property changes. This is done by assigning a {@link Bindable} annotation to the property's
 * getter method.
 */
public class DetailsViewModel extends BaseObservable {

    private Movie mMovie;
    private TrailersAdapter mTrailersAdapter;
    private LinearLayoutManager mLayoutManager;

    public DetailsViewModel() {

    }

    @Bindable
    public Movie getMovie() {
        return this.mMovie;
    }

    void setMovie(Movie movie) {
        this.mMovie = movie;
        notifyPropertyChanged(BR.movie);
    }

    @Bindable
    public TrailersAdapter getTrailersAdapter() {
        return this.mTrailersAdapter;
    }

    void setAdapter(TrailersAdapter adapter) {
        this.mTrailersAdapter = adapter;
        notifyPropertyChanged(BR.trailersAdapter);
    }

    @Bindable
    public LinearLayoutManager getLayoutManager() {
        return this.mLayoutManager;
    }

    void setLayoutManager(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }
}
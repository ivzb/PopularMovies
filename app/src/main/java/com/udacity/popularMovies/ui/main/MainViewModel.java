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

package com.udacity.popularMovies.ui.main;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.GridLayoutManager;

import com.udacity.popularMovies.BR;
import com.udacity.popularMovies.ui.main.adapters.MoviesAdapter;

/**
 * Exposes the data to be used in the {@link MainActivity}.
 * <p>
 * {@link BaseObservable} implements a listener registration mechanism which is notified when a
 * property changes. This is done by assigning a {@link Bindable} annotation to the property's
 * getter method.
 */
public class MainViewModel extends BaseObservable {

    private MoviesAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private boolean mIsLoading;

    public MainViewModel() {

    }

    @Bindable
    public MoviesAdapter getAdapter() {
        return this.mAdapter;
    }

    void setAdapter(MoviesAdapter adapter) {
        this.mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
        notifyPropertyChanged(BR.moviesSize);
    }

    @Bindable
    public GridLayoutManager getLayoutManager() {
        return this.mLayoutManager;
    }

    void setLayoutManager(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

    @Bindable
    public int getMoviesSize() {
        if (mAdapter == null) return 0;
        return this.mAdapter.getItemCount();
    }
}
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

    public MainViewModel() {

    }

    @Bindable
    public MoviesAdapter getAdapter() {
        return this.mAdapter;
    }

    void setAdapter(MoviesAdapter adapter) {
        this.mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Bindable
    public GridLayoutManager getLayoutManager() {
        return this.mLayoutManager;
    }

    void setLayoutManager(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }
}
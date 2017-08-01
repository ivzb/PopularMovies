package com.udacity.popularMovies.utils.adapterSetters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.udacity.popularMovies.ui.main.adapters.MoviesAdapter;

public class Movies {

    /**
     * Initializes RecyclerView adapter
     */
    // CategoriesFragment
    @BindingAdapter({ "adapter:moviesAdapter", "adapter:layoutManager" })
    public static void setRvMoviesAdapter(RecyclerView view, final MoviesAdapter adapter, final GridLayoutManager layoutManager) {
        if (adapter != null && layoutManager != null) {
            view.setAdapter(adapter);
            view.setLayoutManager(layoutManager);
        }
    }
}
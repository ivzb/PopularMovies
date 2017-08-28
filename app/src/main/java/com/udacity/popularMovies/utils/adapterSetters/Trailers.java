package com.udacity.popularMovies.utils.adapterSetters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.udacity.popularMovies.ui.details.adapters.TrailersAdapter;

public class Trailers {

    // CategoriesFragment
    @BindingAdapter({ "bind:trailersAdapter", "adapter:layoutManager" })
    public static void setRvTrailersAdapter(
            RecyclerView view,
            final TrailersAdapter adapter,
            final LinearLayoutManager layoutManager) {

        if (adapter != null && layoutManager != null) {
            view.setAdapter(adapter);
            view.setLayoutManager(layoutManager);
        }
    }
}
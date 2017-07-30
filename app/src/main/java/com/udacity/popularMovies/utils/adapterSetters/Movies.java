package com.udacity.popularMovies.utils.adapterSetters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.udacity.popularMovies.ui.main.adapters.MoviesAdapter;

public class Movies {

    /**
     * Initializes RecyclerView adapter and loads automatically more items as
     * the user scrolls through the items (aka infinite scroll)
     * <p>
     * Creates the {@code android:onLoadMore} for a {@link EndlessRecyclerViewScrollListener}
     * that takes a {@link CategoriesContract.Presenter}.
     */
    // CategoriesFragment
    @BindingAdapter({ /*"adapter:onLoadMore",*/ "adapter:moviesAdapter", "adapter:layoutManager" })
    public static void setRvMoviesAdapter(RecyclerView view, final MoviesAdapter adapter, final GridLayoutManager layoutManager) {
        if (adapter != null && layoutManager != null) {
            view.setAdapter(adapter);
            view.setLayoutManager(layoutManager);

//            // Retain an instance so that you can call `resetState()` for fresh searches
//            EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
//                @Override
//                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                    // Triggered only when new data needs to be appended to the list
//                    // Add whatever code is needed to append new items to the bottom of the list
//                    Category category = adapter.getCategory();
//                    presenter.loadAchievements(category, true);
//                }
//            };
//            // Adds the scroll listener to RecyclerView
//            view.addOnScrollListener(scrollListener);
        }


    }
}
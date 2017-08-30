package com.udacity.popularMovies.utils.adapterSetters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.udacity.popularMovies.ui.details.adapters.ReviewsAdapter;

public class Reviews {

    @BindingAdapter({ "bind:reviewsAdapter", "adapter:layoutManager" })
    public static void setRvReviewsAdapter(
            RecyclerView view,
            final ReviewsAdapter adapter,
            final LinearLayoutManager layoutManager) {

        if (adapter != null && layoutManager != null) {
            view.setAdapter(adapter);
            view.setLayoutManager(layoutManager);
        }
    }
}
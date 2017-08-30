package com.udacity.popularMovies.ui.details.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.popularMovies.BR;
import com.udacity.popularMovies.data.network.model.ReviewsResponse;
import com.udacity.popularMovies.databinding.ReviewRecyclerItemBinding;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private final List<ReviewsResponse.Review> mReviews;

    public ReviewsAdapter(List<ReviewsResponse.Review> reviews) {
        this.mReviews = reviews;
    }

    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ReviewRecyclerItemBinding binding = ReviewRecyclerItemBinding.inflate(layoutInflater, parent, false);

        return new ReviewsAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ReviewsAdapter.ViewHolder viewHolder, int position) {
        ReviewsResponse.Review review = this.mReviews.get(position);
        viewHolder.setBinding(review);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return this.mReviews.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ReviewRecyclerItemBinding binding;

        ViewHolder(ReviewRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        ReviewRecyclerItemBinding getBinding() {
            return this.binding;
        }

        void setBinding(ReviewsResponse.Review review) {
            ReviewRecyclerItemBinding binding = this.getBinding();

            binding.setVariable(BR.review, review);
            binding.executePendingBindings();
        }
    }
}
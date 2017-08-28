package com.udacity.popularMovies.ui.details.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.popularMovies.BR;
import com.udacity.popularMovies.data.network.model.VideosResponse;
import com.udacity.popularMovies.databinding.TrailerRecyclerItemBinding;
import com.udacity.popularMovies.ui.details.DetailsItemActionHandler;

import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {

    private final List<VideosResponse.Video> mTrailers;
    private final DetailsItemActionHandler mItemActionHandler;

    public TrailersAdapter(List<VideosResponse.Video> trailers, DetailsItemActionHandler itemActionHandler) {
        this.mTrailers = trailers;
        this.mItemActionHandler = itemActionHandler;
    }

    @Override
    public TrailersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        TrailerRecyclerItemBinding binding = TrailerRecyclerItemBinding.inflate(layoutInflater, parent, false);

        return new TrailersAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TrailersAdapter.ViewHolder viewHolder, int position) {
        VideosResponse.Video trailer = this.mTrailers.get(position);
        viewHolder.setBinding(trailer, this.mItemActionHandler);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return this.mTrailers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TrailerRecyclerItemBinding binding;

        ViewHolder(TrailerRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        TrailerRecyclerItemBinding getBinding() {
            return this.binding;
        }

        void setBinding(VideosResponse.Video trailer, DetailsItemActionHandler itemActionHandler) {
            TrailerRecyclerItemBinding binding = this.getBinding();

            binding.setVariable(BR.trailer, trailer);
            binding.setVariable(BR.itemActionHandler, itemActionHandler);
            binding.executePendingBindings();
        }
    }
}
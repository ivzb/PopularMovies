package com.udacity.popularMovies.ui.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.popularMovies.BR;
import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.databinding.MovieRecyclerItemBinding;
import com.udacity.popularMovies.ui.main.MainItemActionHandler;

import java.util.Collection;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final List<Movie> mMovies;
    private final MainItemActionHandler mItemActionHandler;

    public MoviesAdapter(List<Movie> movies, MainItemActionHandler itemActionHandler) {
        this.mMovies = movies;
        this.mItemActionHandler = itemActionHandler;
    }

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        MovieRecyclerItemBinding binding = MovieRecyclerItemBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movie movie = this.mMovies.get(position);
        viewHolder.setBinding(movie, this.mItemActionHandler);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        if (mMovies == null) return 0;
        return this.mMovies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final MovieRecyclerItemBinding binding;

        ViewHolder(MovieRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        MovieRecyclerItemBinding getBinding() {
            return this.binding;
        }

        void setBinding(Movie movie, MainItemActionHandler itemActionHandler) {
            MovieRecyclerItemBinding binding = this.getBinding();

            binding.setVariable(BR.movie, movie);
            binding.setVariable(BR.itemActionHandler, itemActionHandler);
            binding.executePendingBindings();
        }
    }
}
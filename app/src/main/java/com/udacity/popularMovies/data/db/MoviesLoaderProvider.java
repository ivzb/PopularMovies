package com.udacity.popularMovies.data.db;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.udacity.popularMovies.di.ApplicationContext;

import javax.inject.Inject;

import static dagger.internal.Preconditions.checkNotNull;

public class MoviesLoaderProvider {

    private final Context mContext;

    @Inject
    public MoviesLoaderProvider(@ApplicationContext Context context) {
        mContext = context;
    }

    public Loader<Cursor> createLoader() {
        return new CursorLoader(
                mContext,
                DbContract.MovieEntry.CONTENT_URI,
                DbContract.MovieEntry.COLUMNS,
                null, null, null);
    }
}

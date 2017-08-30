package com.udacity.popularMovies.utils;

import android.content.ContentValues;

import com.udacity.popularMovies.data.db.DbContract;
import com.udacity.popularMovies.data.network.model.Movie;

import static com.udacity.popularMovies.data.db.DbContract.MovieEntry.*;

public class ContentValuesUtils {

    public static ContentValues from(Movie movie) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, movie.getId());
        values.put(COLUMN_VOTE_COUNT, movie.getVoteCount());
        values.put(COLUMN_VIDEO, movie.isVideo());
        values.put(COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_POPULARITY, movie.getPopularity());
        values.put(COLUMN_POSTER_PATH, movie.getPosterPath());
        values.put(COLUMN_ORIGINAL_LANGUAGE, movie.getOriginalLanguage());
        values.put(COLUMN_ORIGINAL_TITLE, movie.getOriginalTitle());
        values.put(COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        values.put(COLUMN_ADULT, movie.isAdult());
        values.put(COLUMN_OVERVIEW, movie.getOverview());
        values.put(COLUMN_RELEASE_DATE, movie.getReleaseDate());

        return values;
    }
}

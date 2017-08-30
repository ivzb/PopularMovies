package com.udacity.popularMovies.utils;

import android.database.Cursor;

import com.udacity.popularMovies.data.network.model.Movie;

import static com.udacity.popularMovies.data.db.DbContract.MovieEntry.INDEXES.*;

public class CursorUtils {

    public static Movie from(Cursor cursor) {
        int id = cursor.getInt(COLUMN_ID);
        int voteCount = cursor.getInt(COLUMN_VOTE_COUNT);
        boolean isVideo = cursor.getInt(COLUMN_VIDEO) > 0;
        double voteAverage = cursor.getDouble(COLUMN_VOTE_AVERAGE);
        String title = cursor.getString(COLUMN_TITLE);
        double popularity = cursor.getDouble(COLUMN_POPULARITY);
        String posterPath = cursor.getString(COLUMN_POSTER_PATH);
        String originalLanguage = cursor.getString(COLUMN_ORIGINAL_LANGUAGE);
        String originalTitle = cursor.getString(COLUMN_ORIGINAL_TITLE);
        String backdropPath = cursor.getString(COLUMN_BACKDROP_PATH);
        boolean isAdult = cursor.getInt(COLUMN_ADULT) > 0;
        String overview = cursor.getString(COLUMN_OVERVIEW);
        String date = cursor.getString(COLUMN_RELEASE_DATE);

        return new Movie(id, voteCount, isVideo, voteAverage, title, overview,
                popularity, posterPath, originalLanguage, originalTitle,
                backdropPath, isAdult, date);
    }
}

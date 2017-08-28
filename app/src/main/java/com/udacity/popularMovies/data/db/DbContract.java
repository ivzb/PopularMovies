package com.udacity.popularMovies.data.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DbContract {

    public static final String CONTENT_AUTHORITY = "com.udacity.popularMovies";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_FAVORITE_MOVIES = "favorite_movies";

    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_FAVORITE_MOVIES)
                .build();

        public static final String TABLE_NAME = "favorite_movies";

        public static final String COLUMN_ID = "movie_id";

        public static final String COLUMN_VOTE_COUNT = "vote_count";

        public static final String COLUMN_VIDEO = "video";

        public static final String COLUMN_VOTE_AVERAGE = "vote_average";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_POPULARITY = "popularity";

        public static final String COLUMN_POSTER_PATH = "poster_path";

        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";

        public static final String COLUMN_ORIGINAL_TITLE = "original_title";

        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";

        public static final String COLUMN_ADULT = "adult";

        public static final String COLUMN_OVERVIEW = "overview";

        public static final String COLUMN_RELEASE_DATE = "release_date";
    }
}
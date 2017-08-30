package com.udacity.popularMovies.data.db;

import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

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

        public static String[] COLUMNS = new String[] {
                COLUMN_ID,
                COLUMN_VOTE_COUNT,
                COLUMN_VIDEO,
                COLUMN_VOTE_AVERAGE,
                COLUMN_TITLE,
                COLUMN_POPULARITY,
                COLUMN_POSTER_PATH,
                COLUMN_ORIGINAL_LANGUAGE,
                COLUMN_ORIGINAL_TITLE,
                COLUMN_BACKDROP_PATH,
                COLUMN_ADULT,
                COLUMN_OVERVIEW,
                COLUMN_RELEASE_DATE
        };

        public interface INDEXES {
            int COLUMN_ID = 1;
            int COLUMN_VOTE_COUNT = 2;
            int COLUMN_VIDEO = 3;
            int COLUMN_VOTE_AVERAGE = 4;
            int COLUMN_TITLE = 5;
            int COLUMN_POPULARITY = 6;
            int COLUMN_POSTER_PATH = 7;
            int COLUMN_ORIGINAL_LANGUAGE = 8;
            int COLUMN_ORIGINAL_TITLE = 9;
            int COLUMN_BACKDROP_PATH = 10;
            int COLUMN_ADULT = 11;
            int COLUMN_OVERVIEW = 12;
            int COLUMN_RELEASE_DATE = 13;
        }

        public static Uri buildMovieUriWithId(int id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(String.valueOf(id))
                    .build();
        }

        public static String getSqlSelectForMovie() {
            return MovieEntry.COLUMN_ID + " = ?";
        }
    }
}
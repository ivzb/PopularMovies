package com.udacity.popularMovies.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.udacity.popularMovies.data.db.DbContract.MovieEntry;

public class AppDbHelper extends SQLiteOpenHelper implements DbHelper {

    private static final String DATABASE_NAME = "popular_movies.db";

    private static final int DATABASE_VERSION = 1;

    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_WEATHER_TABLE =
                "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
                        MovieEntry._ID                      + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MovieEntry.COLUMN_ID                + " INTEGER NOT NULL, " +
                        MovieEntry.COLUMN_VOTE_COUNT        + " INTEGER NOT NULL," +
                        MovieEntry.COLUMN_VIDEO             + " BOOLEAN NOT NULL, " +
                        MovieEntry.COLUMN_VOTE_AVERAGE      + " REAL NOT NULL, " +
                        MovieEntry.COLUMN_TITLE             + " TEXT NOT NULL, " +
                        MovieEntry.COLUMN_POPULARITY        + " REAL NOT NULL, " +
                        MovieEntry.COLUMN_POSTER_PATH       + " TEXT NOT NULL, " +
                        MovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
                        MovieEntry.COLUMN_ORIGINAL_TITLE    + " TEXT NOT NULL, " +
                        MovieEntry.COLUMN_BACKDROP_PATH     + " TEXT NOT NULL, " +
                        MovieEntry.COLUMN_ADULT             + " BOOLEAN NOT NULL, " +
                        MovieEntry.COLUMN_OVERVIEW          + " TEXT NOT NULL, " +
                        MovieEntry.COLUMN_RELEASE_DATE      + " TEXT NOT NULL, " +
                        " UNIQUE (" + MovieEntry.COLUMN_ID + ") ON CONFLICT REPLACE);";

        db.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
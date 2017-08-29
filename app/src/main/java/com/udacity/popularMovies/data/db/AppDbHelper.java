package com.udacity.popularMovies.data.db;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
//import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
//import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.Query;
import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.MovieStorIOContentResolverDeleteResolver;
import com.udacity.popularMovies.data.network.model.MovieStorIOContentResolverGetResolver;
import com.udacity.popularMovies.data.network.model.MovieStorIOContentResolverPutResolver;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

//import io.reactivex.Observable;
//import io.reactivex.schedulers.Schedulers;

import com.udacity.popularMovies.data.db.DbContract.MovieEntry;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Observable;

@Singleton
public class AppDbHelper extends SQLiteOpenHelper implements DbHelper {

    public static final String DATABASE_NAME = "popular_movies.db";

    private static final int DATABASE_VERSION = 1;

    private StorIOContentResolver mStorIOContentResolver;

    public AppDbHelper(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mStorIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(context.getContentResolver())
                .addTypeMapping(Movie.class, ContentResolverTypeMapping.<Movie>builder()
                        .putResolver(new MovieStorIOContentResolverPutResolver())
                        .getResolver(new MovieStorIOContentResolverGetResolver())
                        .deleteResolver(new MovieStorIOContentResolverDeleteResolver())
                        .build()
                ).build();
    }

    @Override
    public Observable<List<Movie>> getFavoriteMovies() {
        rx.Observable<List<Movie>> obs = mStorIOContentResolver.get()
                .listOfObjects(Movie.class)
                .withQuery(Query.builder().uri(DbContract.MovieEntry.CONTENT_URI).build())
                .prepare()
                .asRxObservable();

        return RxJavaInterop.toV2Observable(obs);
    }

    @Override
    public void saveFavoriteMovie(Movie movie) {
        mStorIOContentResolver.put().object(movie).prepare().executeAsBlocking();
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

    private Callable<List<Movie>> getMovies(SQLiteDatabase db) {
        return new Callable<List<Movie>>() {
            @Override
            public List<Movie> call() {
                // select * from users where _id is userId
                return null;
            }
        };
    }
}
package com.udacity.popularMovies.db;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.popularMovies.data.db.AppDbHelper;
import com.udacity.popularMovies.data.db.DbContract;
import com.udacity.popularMovies.data.db.MoviesProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.udacity.popularMovies.data.db.DbContract.MovieEntry.buildMovieUriWithId;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class TestMoviesProvider {

    private final Context mContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void setUp() throws Exception {
        deleteAllRecordsFromMoviesTable();
    }

    @Test
    public void testProviderRegistry() {
        String packageName = mContext.getPackageName();
        String weatherProviderClassName = MoviesProvider.class.getName();
        ComponentName componentName = new ComponentName(packageName, weatherProviderClassName);

        try {
            PackageManager pm = mContext.getPackageManager();

            ProviderInfo providerInfo = pm.getProviderInfo(componentName, 0);
            String actualAuthority = providerInfo.authority;
            String expectedAuthority = DbContract.CONTENT_AUTHORITY;

            String incorrectAuthority =
                    "Error: MoviesProvider registered with authority: " + actualAuthority +
                            " instead of expected authority: " + expectedAuthority;

            assertEquals(incorrectAuthority,
                    actualAuthority,
                    expectedAuthority);

        } catch (PackageManager.NameNotFoundException e) {
            String providerNotRegisteredAtAll =
                    "Error: WeatherProvider not registered at " + mContext.getPackageName();
            fail(providerNotRegisteredAtAll);
        }
    }

    @Test
    public void testMoviesQuery() {
        AppDbHelper dbHelper = new AppDbHelper(mContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues testValues = TestUtilities.createTestContentValues();

        long weatherRowId = database.insert(
                DbContract.MovieEntry.TABLE_NAME,
                null,
                testValues);

        String insertFailed = "Unable to insert into the database";
        assertTrue(insertFailed, weatherRowId != -1);

        database.close();

        Cursor cursor = mContext.getContentResolver().query(
                DbContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null);

        TestUtilities.validateThenCloseCursor("testBasicQuery",
                cursor,
                testValues);
    }

    @Test
    public void testMovieQueryById() {
        AppDbHelper dbHelper = new AppDbHelper(mContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues testValues = TestUtilities.createTestContentValues();

        long weatherRowId = database.insert(
                DbContract.MovieEntry.TABLE_NAME,
                null,
                testValues);

        String insertFailed = "Unable to insert into the database";
        assertTrue(insertFailed, weatherRowId != -1);

        database.close();

        Uri movieQueryUri = buildMovieUriWithId(211672);

        Cursor cursor = mContext.getContentResolver().query(
                movieQueryUri,
                null,
                null,
                null,
                null);

        TestUtilities.validateThenCloseCursor("testBasicQuery",
                cursor,
                testValues);
    }

    private void deleteAllRecordsFromMoviesTable() {
        AppDbHelper helper = new AppDbHelper(InstrumentationRegistry.getTargetContext());
        SQLiteDatabase database = helper.getWritableDatabase();

        database.delete(DbContract.MovieEntry.TABLE_NAME, null, null);

        database.close();
    }
}
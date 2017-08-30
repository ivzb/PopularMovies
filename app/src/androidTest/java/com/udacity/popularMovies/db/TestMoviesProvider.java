package com.udacity.popularMovies.db;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.popularMovies.data.db.AppDbHelper;
import com.udacity.popularMovies.data.db.DbContract;
import com.udacity.popularMovies.data.db.MoviesProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void testBasicQuery() {
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

//    @Test
//    public void testBulkInsert() {
//
//        /* Create a new array of ContentValues for weather */
//        ContentValues[] bulkInsertTestContentValues = createBulkInsertTestWeatherValues();
//
//        /*
//         * TestContentObserver allows us to test weather or not notifyChange was called
//         * appropriately. We will use that here to make sure that notifyChange is called when a
//         * deletion occurs.
//         */
//        TestUtilities.TestContentObserver weatherObserver = TestUtilities.getTestContentObserver();
//
//        /*
//         * A ContentResolver provides us access to the content model. We can use it to perform
//         * deletions and queries at our CONTENT_URI
//         */
//        ContentResolver contentResolver = mContext.getContentResolver();
//
//        /* Register a content observer to be notified of changes to data at a given URI (weather) */
//        contentResolver.registerContentObserver(
//                /* URI that we would like to observe changes to */
//                WeatherContract.WeatherEntry.CONTENT_URI,
//                /* Whether or not to notify us if descendants of this URI change */
//                true,
//                /* The observer to register (that will receive notifyChange callbacks) */
//                weatherObserver);
//
//        /* bulkInsert will return the number of records that were inserted. */
//        int insertCount = contentResolver.bulkInsert(
//                /* URI at which to insert data */
//                WeatherContract.WeatherEntry.CONTENT_URI,
//                /* Array of values to insert into given URI */
//                bulkInsertTestContentValues);
//
//        /*
//         * If this fails, it's likely you didn't call notifyChange in your insert method from
//         * your ContentProvider.
//         */
//        weatherObserver.waitForNotificationOrFail();
//
//        /*
//         * waitForNotificationOrFail is synchronous, so after that call, we are done observing
//         * changes to content and should therefore unregister this observer.
//         */
//        contentResolver.unregisterContentObserver(weatherObserver);
//
//        /*
//         * We expect that the number of test content values that we specify in our TestUtility
//         * class were inserted here. We compare that value to the value that the ContentProvider
//         * reported that it inserted. These numbers should match.
//         */
//        String expectedAndActualInsertedRecordCountDoNotMatch =
//                "Number of expected records inserted does not match actual inserted record count";
//        assertEquals(expectedAndActualInsertedRecordCountDoNotMatch,
//                insertCount,
//                BULK_INSERT_RECORDS_TO_INSERT);
//
//        /*
//         * Perform our ContentProvider query. We expect the cursor that is returned will contain
//         * the exact same data that is in testWeatherValues and we will validate that in the next
//         * step.
//         */
//        Cursor cursor = mContext.getContentResolver().query(
//                WeatherContract.WeatherEntry.CONTENT_URI,
//                /* Columns; leaving this null returns every column in the table */
//                null,
//                /* Optional specification for columns in the "where" clause above */
//                null,
//                /* Values for "where" clause */
//                null,
//                /* Sort by date from smaller to larger (past to future) */
//                WeatherContract.WeatherEntry.COLUMN_DATE + " ASC");
//
//        /*
//         * Although we already tested the number of records that the ContentProvider reported
//         * inserting, we are now testing the number of records that the ContentProvider actually
//         * returned from the query above.
//         */
//        assertEquals(cursor.getCount(), BULK_INSERT_RECORDS_TO_INSERT);
//
//        /*
//         * We now loop through and validate each record in the Cursor with the expected values from
//         * bulkInsertTestContentValues.
//         */
//        cursor.moveToFirst();
//        for (int i = 0; i < BULK_INSERT_RECORDS_TO_INSERT; i++, cursor.moveToNext()) {
//            TestUtilities.validateCurrentRecord(
//                    "testBulkInsert. Error validating WeatherEntry " + i,
//                    cursor,
//                    bulkInsertTestContentValues[i]);
//        }
//
//        /* Always close the Cursor! */
//        cursor.close();
//    }
//
//    @Test
//    public void testDeleteAllRecordsFromProvider() {
//
//        /*
//         * Ensure there are records to delete from the database. Due to our setUp method, the
//         * database will not have any records in it prior to this method being run.
//         */
//        testBulkInsert();
//
//        /*
//         * TestContentObserver allows us to test weather or not notifyChange was called
//         * appropriately. We will use that here to make sure that notifyChange is called when a
//         * deletion occurs.
//         */
//        TestUtilities.TestContentObserver weatherObserver = TestUtilities.getTestContentObserver();
//
//        /*
//         * A ContentResolver provides us access to the content model. We can use it to perform
//         * deletions and queries at our CONTENT_URI
//         */
//        ContentResolver contentResolver = mContext.getContentResolver();
//
//        /* Register a content observer to be notified of changes to data at a given URI (weather) */
//        contentResolver.registerContentObserver(
//                /* URI that we would like to observe changes to */
//                WeatherContract.WeatherEntry.CONTENT_URI,
//                /* Whether or not to notify us if descendants of this URI change */
//                true,
//                /* The observer to register (that will receive notifyChange callbacks) */
//                weatherObserver);
//
//        /* Delete all of the rows of data from the weather table */
//        contentResolver.delete(
//                WeatherContract.WeatherEntry.CONTENT_URI,
//                /* Columns; leaving this null returns every column in the table */
//                null,
//                /* Optional specification for columns in the "where" clause above */
//                null);
//
//        /* Perform a query of the data that we've just deleted. This should be empty. */
//        Cursor shouldBeEmptyCursor = contentResolver.query(
//                WeatherContract.WeatherEntry.CONTENT_URI,
//                /* Columns; leaving this null returns every column in the table */
//                null,
//                /* Optional specification for columns in the "where" clause above */
//                null,
//                /* Values for "where" clause */
//                null,
//                /* Sort order to return in Cursor */
//                null);
//
//        /*
//         * If this fails, it's likely you didn't call notifyChange in your delete method from
//         * your ContentProvider.
//         */
//        weatherObserver.waitForNotificationOrFail();
//
//        /*
//         * waitForNotificationOrFail is synchronous, so after that call, we are done observing
//         * changes to content and should therefore unregister this observer.
//         */
//        contentResolver.unregisterContentObserver(weatherObserver);
//
//        /* In some cases, the cursor can be null. That's actually a failure case here. */
//        String cursorWasNull = "Cursor was null.";
//        assertNotNull(cursorWasNull, shouldBeEmptyCursor);
//
//        /* If the count of the cursor is not zero, all records weren't deleted */
//        String allRecordsWereNotDeleted =
//                "Error: All records were not deleted from weather table during delete";
//        assertEquals(allRecordsWereNotDeleted,
//                0,
//                shouldBeEmptyCursor.getCount());
//
//        /* Always close your cursor */
//        shouldBeEmptyCursor.close();
//    }
//
    private void deleteAllRecordsFromMoviesTable() {
        AppDbHelper helper = new AppDbHelper(InstrumentationRegistry.getTargetContext());
        SQLiteDatabase database = helper.getWritableDatabase();

        database.delete(DbContract.MovieEntry.TABLE_NAME, null, null);

        database.close();
    }
}
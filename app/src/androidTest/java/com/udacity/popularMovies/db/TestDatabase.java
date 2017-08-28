package com.udacity.popularMovies.db;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.udacity.popularMovies.data.db.DbContract;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

import static com.udacity.popularMovies.data.db.DbContract.MovieEntry.COLUMN_ID;
import static com.udacity.popularMovies.db.TestUtilities.getConstantNameByStringValue;
import static com.udacity.popularMovies.db.TestUtilities.getStaticIntegerField;
import static com.udacity.popularMovies.db.TestUtilities.getStaticStringField;
import static com.udacity.popularMovies.db.TestUtilities.readableClassNotFound;
import static com.udacity.popularMovies.db.TestUtilities.readableNoSuchField;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class TestDatabase {

    private final Context context = InstrumentationRegistry.getTargetContext();

    private static final String packageName = "com.udacity.popularMovies";
    private static final String dataPackageName = packageName + ".data.db";

    private Class entryClass;
    private Class dbHelperClass;
    private static final String contractName = ".DbContract";
    private static final String entryName = contractName + "$MovieEntry";
    private static final String dbHelperName = ".AppDbHelper";

    private static final String databaseNameVariableName = "DATABASE_NAME";
    private static String REFLECTED_DATABASE_NAME;

    private static final String databaseVersionVariableName = "DATABASE_VERSION";
    private static int REFLECTED_DATABASE_VERSION;

    private static final String tableNameVariableName = "TABLE_NAME";
    private static String REFLECTED_TABLE_NAME;

    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;

    @Before
    public void before() {
        try {
            entryClass = Class.forName(dataPackageName + entryName);

            if (!BaseColumns.class.isAssignableFrom(entryClass)) {
                String entryDoesNotImplementBaseColumns = "MoviesEntry class needs to " +
                        "implement the interface BaseColumns, but does not.";
                fail(entryDoesNotImplementBaseColumns);
            }

            REFLECTED_TABLE_NAME = getStaticStringField(entryClass, tableNameVariableName);

            dbHelperClass = Class.forName(dataPackageName + dbHelperName);

            Class dbHelperSuperclass = dbHelperClass.getSuperclass();

            if (dbHelperSuperclass == null || dbHelperSuperclass.equals(Object.class)) {
                String noExplicitSuperclass =
                        "DbHelper needs to extend SQLiteOpenHelper, but yours currently doesn't extend a class at all.";
                fail(noExplicitSuperclass);
            } else if (dbHelperSuperclass != null) {
                String dbHelperSuperclassName = dbHelperSuperclass.getSimpleName();
                String doesNotExtendOpenHelper =
                        "DbHelper needs to extend SQLiteOpenHelper but yours extends "
                                + dbHelperSuperclassName;

                assertTrue(doesNotExtendOpenHelper,
                        SQLiteOpenHelper.class.isAssignableFrom(dbHelperSuperclass));
            }

            REFLECTED_DATABASE_NAME = getStaticStringField(
                    dbHelperClass, databaseNameVariableName);

            REFLECTED_DATABASE_VERSION = getStaticIntegerField(
                    dbHelperClass, databaseVersionVariableName);

            Constructor dDbHelperCtor = dbHelperClass.getConstructor(Context.class);

            dbHelper = (SQLiteOpenHelper) dDbHelperCtor.newInstance(context);

            context.deleteDatabase(REFLECTED_DATABASE_NAME);

            Method getWritableDatabase = SQLiteOpenHelper.class.getDeclaredMethod("getWritableDatabase");
            database = (SQLiteDatabase) getWritableDatabase.invoke(dbHelper);
        } catch (ClassNotFoundException e) {
            fail(readableClassNotFound(e));
        } catch (NoSuchFieldException e) {
            fail(readableNoSuchField(e));
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        } catch (InstantiationException e) {
            fail(e.getMessage());
        } catch (InvocationTargetException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDatabaseVersionWasIncremented() {
        int expectedDatabaseVersion = 1;
        String databaseVersionShouldBe1 = "Database version should be "
                + expectedDatabaseVersion + " but isn't."
                + "\n Database version: ";

        assertEquals(databaseVersionShouldBe1,
                expectedDatabaseVersion,
                REFLECTED_DATABASE_VERSION);
    }

    @Test
    public void testDuplicateIdInsertBehaviorShouldReplace() {
        ContentValues testValues = TestUtilities.createTestContentValues();

        long originalId = testValues.getAsInteger(COLUMN_ID);

        database.insert(
                DbContract.MovieEntry.TABLE_NAME,
                null,
                testValues);

        database.insert(
                DbContract.MovieEntry.TABLE_NAME,
                null,
                testValues);

        Cursor newIdCursor = database.query(
                REFLECTED_TABLE_NAME,
                new String[]{COLUMN_ID},
                null,
                null,
                null,
                null,
                null);

        String recordWithNewIdNotFound =
                "New record did not overwrite the previous record for the same date.";
        assertTrue(recordWithNewIdNotFound,
                newIdCursor.getCount() == 1);

        newIdCursor.close();
    }

    @Test
    public void testNullColumnConstraints() {
        Cursor tableCursor = database.query(
                REFLECTED_TABLE_NAME,

                null, null, null, null, null, null);

        String[] tableColumnNames = tableCursor.getColumnNames();
        tableCursor.close();

        ContentValues testValues = TestUtilities.createTestContentValues();
        ContentValues testValuesReferenceCopy = new ContentValues(testValues);

        for (String columnName : tableColumnNames) {
            if (columnName.equals(DbContract.MovieEntry._ID)) continue;

            testValues.putNull(columnName);

            long shouldFailRowId = database.insert(
                    REFLECTED_TABLE_NAME,
                    null,
                    testValues);

            String variableName = getConstantNameByStringValue(
                    DbContract.MovieEntry.class,
                    columnName);

            String nullRowInsertShouldFail =
                    "Insert should have failed due to a null value for column: '" + columnName + "'"
                            + ", but didn't."
                            + "\n Check that you've added NOT NULL to " + variableName
                            + " in your create table statement in the MovieEntry class."
                            + "\n Row ID: ";
            assertEquals(nullRowInsertShouldFail,
                    -1,
                    shouldFailRowId);

            testValues.put(columnName, testValuesReferenceCopy.getAsDouble(columnName));
        }

        dbHelper.close();
    }

    @Test
    public void testIntegerAutoincrement() {
        testInsertSingleRecordIntoMoviesTable();

        ContentValues testValues = TestUtilities.createTestContentValues();

        int originalId = testValues.getAsInteger(COLUMN_ID);

        long firstRowId = database.insert(
                REFLECTED_TABLE_NAME,
                null,
                testValues);

        database.delete(
                REFLECTED_TABLE_NAME,
                "_ID == " + firstRowId,
                null);

        testValues.put(COLUMN_ID, originalId + 1);

        long secondRowId = database.insert(
                REFLECTED_TABLE_NAME,
                null,
                testValues);

        String sequentialInsertsDoNotAutoIncrementId =
                "IDs were reused and shouldn't be if autoincrement is setup properly.";

        assertNotSame(sequentialInsertsDoNotAutoIncrementId,
                firstRowId, secondRowId);
    }

    @Test
    public void testOnUpgradeBehavesCorrectly() {
        testInsertSingleRecordIntoMoviesTable();

        dbHelper.onUpgrade(database, 13, 14);

        Cursor tableNameCursor = database.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='" + REFLECTED_TABLE_NAME + "'",
                null);

        int expectedTableCount = 1;
        String shouldHaveSingleTable = "There should only be one table returned from this query.";
        assertEquals(shouldHaveSingleTable,
                expectedTableCount,
                tableNameCursor.getCount());

        tableNameCursor.close();

        Cursor shouldBeEmptyCursor = database.query(
                REFLECTED_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        int expectedRecordCountAfterUpgrade = 0;

        String tableShouldBeEmpty =
                "Movies table should be empty after upgrade, but wasn't."
                        + "\nNumber of records: ";
        assertEquals(tableShouldBeEmpty,
                expectedRecordCountAfterUpgrade,
                shouldBeEmptyCursor.getCount());

        database.close();
    }

    @Test
    public void testCreateDb() {
        final HashSet<String> tableNameHashSet = new HashSet<>();

        tableNameHashSet.add(REFLECTED_TABLE_NAME);

        String databaseIsNotOpen = "The database should be open and isn't";
        assertEquals(databaseIsNotOpen,
                true,
                database.isOpen());

        Cursor tableNameCursor = database.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table'",
                null);

        String errorInCreatingDatabase =
                "Error: This means that the database has not been created correctly";
        assertTrue(errorInCreatingDatabase,
                tableNameCursor.moveToFirst());

        do {
            tableNameHashSet.remove(tableNameCursor.getString(0));
        } while (tableNameCursor.moveToNext());

        assertTrue("Error: Your database was created without the expected tables.",
                tableNameHashSet.isEmpty());

        tableNameCursor.close();
    }

    @Test
    public void testInsertSingleRecordIntoMoviesTable() {
        ContentValues testValues = TestUtilities.createTestContentValues();

        long rowId = database.insert(
                REFLECTED_TABLE_NAME,
                null,
                testValues);

        int valueOfIdIfInsertFails = -1;
        String insertFailed = "Unable to insert into the database";
        assertNotSame(insertFailed,
                valueOfIdIfInsertFails,
                rowId);

        Cursor cursor = database.query(
                REFLECTED_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        String emptyQueryError = "Error: No Records returned from query";
        assertTrue(emptyQueryError,
                cursor.moveToFirst());

        String expectedMovieDidntMatchActual =
                "Expected movie values didn't match actual values.";
        TestUtilities.validateCurrentRecord(expectedMovieDidntMatchActual,
                cursor,
                testValues);

        assertFalse("Error: More than one record returned from movie query",
                cursor.moveToNext());

        cursor.close();
    }
}
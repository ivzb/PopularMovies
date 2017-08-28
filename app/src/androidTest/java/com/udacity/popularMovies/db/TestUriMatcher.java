package com.udacity.popularMovies.db;

import android.content.UriMatcher;
import android.net.Uri;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.popularMovies.data.db.DbContract;
import com.udacity.popularMovies.data.db.MoviesProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.udacity.popularMovies.db.TestUtilities.getStaticIntegerField;
import static com.udacity.popularMovies.db.TestUtilities.readableNoSuchField;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class TestUriMatcher {

    private static final Uri TEST_MOVIES_DIR = DbContract.MovieEntry.CONTENT_URI;

    private static final String moviesCodeVariableName = "CODE_MOVIES";
    private static int REFLECTED_MOVIES_CODE;

    private UriMatcher testMatcher;

    @Before
    public void before() {
        try {

            Method buildUriMatcher = MoviesProvider.class.getDeclaredMethod("buildUriMatcher");
            testMatcher = (UriMatcher) buildUriMatcher.invoke(MoviesProvider.class);

            REFLECTED_MOVIES_CODE = getStaticIntegerField(
                    MoviesProvider.class,
                    moviesCodeVariableName);

        } catch (NoSuchFieldException e) {
            fail(readableNoSuchField(e));
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        } catch (NoSuchMethodException e) {
            String noBuildUriMatcherMethodFound =
                    "It doesn't appear that you have created a method called buildUriMatcher in " +
                            "the MoviesProvider class.";
            fail(noBuildUriMatcherMethodFound);
        } catch (InvocationTargetException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testUriMatcher() {
        String moviesUriDoesNotMatch = "Error: The CODE_MOVIES URI was matched incorrectly.";
        int actualMoviesCode = testMatcher.match(TEST_MOVIES_DIR);
        int expectedMoviesCode = REFLECTED_MOVIES_CODE;
        assertEquals(moviesUriDoesNotMatch,
                expectedMoviesCode,
                actualMoviesCode);
    }
}
package com.udacity.popularMovies.di.module;

import android.app.Application;
import android.content.Context;

import com.udacity.popularMovies.BuildConfig;
import com.udacity.popularMovies.R;
import com.udacity.popularMovies.data.AppDataManager;
import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.data.network.ApiHeader;
import com.udacity.popularMovies.data.network.ApiHelper;
import com.udacity.popularMovies.data.network.AppApiHelper;
import com.udacity.popularMovies.di.ApiInfo;
import com.udacity.popularMovies.di.ApplicationContext;
import com.udacity.popularMovies.di.DatabaseInfo;
import com.udacity.popularMovies.di.PreferenceInfo;
import com.udacity.popularMovies.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by amitshekhar on 03/02/17.
 */
@Module
public class ApplicationTestModule {

    private final Application mApplication;

    public ApplicationTestModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey) {
        return new ApiHeader.ProtectedApiHeader(apiKey);
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}

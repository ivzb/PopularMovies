/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.udacity.popularMovies.di.module;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.udacity.popularMovies.BuildConfig;
import com.udacity.popularMovies.R;
import com.udacity.popularMovies.data.AppDataManager;
import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.data.network.ApiEndPoint;
import com.udacity.popularMovies.data.network.ApiHelper;
import com.udacity.popularMovies.data.network.AppApiHelper;
import com.udacity.popularMovies.di.ApiBaseUrl;
import com.udacity.popularMovies.di.ApiInfo;
import com.udacity.popularMovies.di.ApplicationContext;
import com.udacity.popularMovies.di.DateFormat;
import com.udacity.popularMovies.utils.AppConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
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
    @ApiBaseUrl
    String provideApiBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    @ImageBaseUrl
    String provideImageBaseUrl() {
        return BuildConfig.BASE_IMAGE_URL;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @DateFormat
    String provideDateFormat() {
        return AppConstants.DATE_FORMAT;
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
    AppApiHelper provideAppApiHelper(ApiEndPoint api) {
        return new AppApiHelper(api);
    }

    @Provides
    @Singleton
    Gson provideParser(@DateFormat String dateFormat) {
        return new GsonBuilder()
                .setDateFormat(dateFormat)
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(
            @ApiBaseUrl final String apiBaseUrl,
            @ApiInfo final String apiKey,
            Gson parser) {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@Nonnull Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        HttpUrl url = originalRequest.url()
                                .newBuilder()
                                .addQueryParameter("api_key", apiKey)
                                .build();

                        Request requestWithApiKey = originalRequest
                                .newBuilder()
                                .url(url)
                                .build();

                        return chain.proceed(requestWithApiKey);
                    }
                })
                .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(apiBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(parser))
                .build();
    }

    @Provides
    @Singleton
    ApiEndPoint provideApi(Retrofit retrofit) {
        return retrofit.create(ApiEndPoint.class);
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

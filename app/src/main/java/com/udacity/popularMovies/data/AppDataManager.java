package com.udacity.popularMovies.data;

import android.content.Context;

import com.udacity.popularMovies.data.network.ApiHeader;
import com.udacity.popularMovies.data.network.ApiHelper;
import com.udacity.popularMovies.data.network.model.BlogResponse;
import com.udacity.popularMovies.data.network.model.OpenSourceResponse;
import com.udacity.popularMovies.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context, ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Observable<BlogResponse> getBlogApiCall() {
        return mApiHelper.getBlogApiCall();
    }

    @Override
    public Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return mApiHelper.getOpenSourceApiCall();
    }
}

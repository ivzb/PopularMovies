package com.udacity.popularMovies.data.network;

import com.udacity.popularMovies.data.network.model.BlogResponse;
import com.udacity.popularMovies.data.network.model.OpenSourceResponse;

import io.reactivex.Observable;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<BlogResponse> getBlogApiCall();

    Observable<OpenSourceResponse> getOpenSourceApiCall();
}

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

package com.udacity.popularMovies.data.network;

import com.udacity.popularMovies.data.callbacks.GetCallback;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.data.network.model.ReviewsResponse;
import com.udacity.popularMovies.data.network.model.VideosResponse;

public interface ApiHelper {

    ApiEndPoint getApi();

    void getPopularMoviesApiCall(GetCallback<MoviesResponse> callback);

    void getTopRatedMoviesApiCall(GetCallback<MoviesResponse> callback);

    void getTrailersApiCall(int movieId, GetCallback<VideosResponse> callback);

    void getReviewsApiCall(int movieId, GetCallback<ReviewsResponse> callback);
}
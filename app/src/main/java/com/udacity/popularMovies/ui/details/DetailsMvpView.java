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

package com.udacity.popularMovies.ui.details;

import android.content.Intent;

import com.udacity.popularMovies.data.network.model.ReviewsResponse;
import com.udacity.popularMovies.data.network.model.VideosResponse;
import com.udacity.popularMovies.ui.base.MvpView;

import java.util.List;

public interface DetailsMvpView extends MvpView {

    void loadTrailers(List<VideosResponse.Video> videos);

    void loadReviews(List<ReviewsResponse.Review> reviews);

    void playTrailer(Intent intent);

    void setFavoriteMovie(boolean isFavorite);
}
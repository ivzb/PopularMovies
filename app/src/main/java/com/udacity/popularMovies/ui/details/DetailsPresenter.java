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

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.VideosResponse;
import com.udacity.popularMovies.ui.base.BasePresenter;
import com.udacity.popularMovies.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class DetailsPresenter<V extends DetailsMvpView> extends BasePresenter<V> implements
        DetailsMvpPresenter<V> {

    private static final String TAG = "DetailsPresenter";

    @Inject
    DetailsPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadTrailersFor(int movieId) {
        getMvpView().showLoading();

        Observable<VideosResponse> observable = getDataManager()
                .getTrailersApiCall(movieId);

        getCompositeDisposable()
                .add(observable
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<VideosResponse>() {
                            @Override
                            public void accept(VideosResponse videosResponse) throws Exception {
                                if (isViewUnattached()) {
                                    return;
                                }

                                getMvpView().hideLoading();

                                if (videosResponse == null) return;

                                getMvpView().loadTrailers(videosResponse.getResults());
                            }
                        }));
    }

    @Override
    public void onTrailerClicked(String key) {
        try {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
            getMvpView().playTrailer(appIntent);
        } catch (ActivityNotFoundException ex) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + key));
            getMvpView().playTrailer(webIntent);
        }
    }

    @Override
    public void favoriteMovie(Movie movie) {
        getDataManager().saveFavoriteMovie(movie);
    }
}
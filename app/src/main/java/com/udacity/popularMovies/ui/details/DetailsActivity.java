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

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.udacity.popularMovies.R;
import com.udacity.popularMovies.data.db.DbContract;
import com.udacity.popularMovies.data.network.model.Movie;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.data.network.model.VideosResponse;
import com.udacity.popularMovies.databinding.ActivityDetailsBinding;
import com.udacity.popularMovies.ui.base.BaseActivity;
import com.udacity.popularMovies.ui.details.adapters.TrailersAdapter;
import com.udacity.popularMovies.ui.main.MainActivity;
import com.udacity.popularMovies.ui.main.MainItemActionHandler;
import com.udacity.popularMovies.ui.main.adapters.MoviesAdapter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import static com.udacity.popularMovies.data.db.DbContract.MovieEntry.buildMovieUriWithId;

public class DetailsActivity extends BaseActivity implements DetailsMvpView {

    @Inject
    DetailsMvpPresenter<DetailsMvpView> mPresenter;

    @Inject
    DetailsViewModel mViewModel;

    @Inject
    LinearLayoutManager mLayoutManager;

    Movie mMovie;

    private ActivityDetailsBinding mBinding;

    public static Intent getStartIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivityComponent().inject(this);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(MainActivity.BUNDLE_MOVIE)) {
                this.mMovie = Parcels.unwrap(savedInstanceState.getParcelable(MainActivity.BUNDLE_MOVIE));
            }
        }

        Bundle intentBundle = getIntent().getExtras();

        if (intentBundle != null) {
            if (intentBundle.containsKey(MainActivity.BUNDLE_MOVIE)) {
                this.mMovie = Parcels.unwrap(intentBundle.getParcelable(MainActivity.BUNDLE_MOVIE));
            }
        }

        mViewModel.setMovie(mMovie);
        mViewModel.setLayoutManager(this.mLayoutManager);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        mBinding.setViewModel(mViewModel);

        mPresenter.setMovieId(mMovie.getId());
        mPresenter.onAttach(this);

        mBinding.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.favoriteMovie(mMovie);
                mViewModel.setFavorite(true);
            }
        });

        mBinding.btnUnfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.unfavoriteMovie(mMovie);
                mViewModel.setFavorite(false);
            }
        });

        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putParcelable(MainActivity.BUNDLE_MOVIE, Parcels.wrap(this.mMovie));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(MainActivity.BUNDLE_MOVIE)) {
                this.mMovie = Parcels.unwrap(savedInstanceState.getParcelable(MainActivity.BUNDLE_MOVIE));
            }
        }
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mBinding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mPresenter.loadTrailersFor(mMovie.getId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void loadTrailers(List<VideosResponse.Video> videos) {
        DetailsItemActionHandler itemActionHandler = new DetailsItemActionHandler(mPresenter);
        TrailersAdapter adapter = new TrailersAdapter(videos, itemActionHandler);
        this.mViewModel.setAdapter(adapter);
    }

    @Override
    public void playTrailer(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void setFavoriteMovie(boolean isFavorite) {
        mViewModel.setFavorite(isFavorite);
    }
}
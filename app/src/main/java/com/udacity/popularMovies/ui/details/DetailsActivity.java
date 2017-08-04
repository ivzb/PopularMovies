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
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;

import com.udacity.popularMovies.R;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.databinding.ActivityDetailsBinding;
import com.udacity.popularMovies.ui.base.BaseActivity;
import com.udacity.popularMovies.ui.main.MainActivity;

import org.parceler.Parcels;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity implements DetailsMvpView {

    @Inject
    DetailsMvpPresenter<DetailsMvpView> mPresenter;

    @Inject
    DetailsViewModel mViewModel;

    MoviesResponse.Movie mMovie;

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

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        mBinding.setViewModel(mViewModel);

        mPresenter.onAttach(this);

        setUp();
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
}
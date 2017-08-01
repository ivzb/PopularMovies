package com.udacity.popularMovies.ui.main;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.udacity.popularMovies.R;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.databinding.ActivityMainBinding;
import com.udacity.popularMovies.ui.base.BaseActivity;
import com.udacity.popularMovies.ui.main.adapters.MoviesAdapter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String BUNDLE_SORT_BY = "SortBy";

    private SortBy mSortBy;

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Inject
    MainViewModel mViewModel;

    @Inject
    GridLayoutManager mLayoutManager;

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(BUNDLE_SORT_BY)) {
                this.mSortBy = Parcels.unwrap(savedInstanceState.getParcelable(BUNDLE_SORT_BY));
            }
        }

        getActivityComponent().inject(this);

        mViewModel.setLayoutManager(this.mLayoutManager);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setViewModel(this.mViewModel);

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(BUNDLE_SORT_BY)) {
                this.mSortBy = Parcels.unwrap(savedInstanceState.getParcelable(BUNDLE_SORT_BY));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (this.mSortBy != null) {
            outState.putParcelable(BUNDLE_SORT_BY, Parcels.wrap(this.mSortBy));
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();

        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        switch (item.getItemId()) {
            case R.id.action_sort:
                this.showSortDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mBinding.toolbar);
        mPresenter.onViewInitialized(mSortBy);
    }

    @Override
    public void refreshMovies(List<MoviesResponse.Movie> movies) {
        MoviesAdapter adapter = new MoviesAdapter(movies);
        this.mViewModel.setAdapter(adapter);
    }

    @Override
    public void showSortDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Sort movies by");
        alertDialogBuilder
                .setCancelable(false)
                .setSingleChoiceItems(SortBy.getValues(), -1, null)
                .setPositiveButton("Sort", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ListView lw = ((AlertDialog) dialog).getListView();
                        String sortedBy = (String)lw.getAdapter().getItem(lw.getCheckedItemPosition());

                        mSortBy = SortBy.getByName(sortedBy);
                        mPresenter.onSortClicked(mSortBy);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
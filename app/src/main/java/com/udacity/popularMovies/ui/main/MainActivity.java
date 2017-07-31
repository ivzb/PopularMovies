package com.udacity.popularMovies.ui.main;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.popularMovies.R;
import com.udacity.popularMovies.data.network.model.MoviesResponse;
import com.udacity.popularMovies.databinding.ActivityMainBinding;
import com.udacity.popularMovies.ui.base.BaseActivity;
import com.udacity.popularMovies.ui.main.adapters.MoviesAdapter;
import com.udacity.popularMovies.ui.main.sort.SortBy;
import com.udacity.popularMovies.ui.main.sort.SortDialog;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView, SortDialog.InterfaceCommunicator {

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

        getActivityComponent().inject(this);

        mViewModel.setLayoutManager(this.mLayoutManager);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setViewModel(this.mViewModel);

        mPresenter.onAttach(this);

        setUp();
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
            case R.id.action_cut:
                return true;
            case R.id.action_copy:
                return true;
            case R.id.action_share:
                return true;
            case R.id.action_delete:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mBinding.toolbar);
        mPresenter.onViewInitialized();
    }

    @Override
    public void refreshMovies(List<MoviesResponse.Movie> movies) {
        MoviesAdapter adapter = new MoviesAdapter(movies);
        this.mViewModel.setAdapter(adapter);
    }

    @Override
    public void showSortDialog() {
        SortDialog.newInstance().show(getSupportFragmentManager());
    }

    @Override
    public void sendRequestCode(int code, SortBy sortBy) {
        if (code == 1) {
            // use sortBy
        }
    }
}
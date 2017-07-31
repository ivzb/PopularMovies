package com.udacity.popularMovies.ui.main.sort;

import com.udacity.popularMovies.ui.base.MvpPresenter;

public interface SortDialogMvpPresenter<V extends SortDialogMvpView> extends MvpPresenter<V> {

    void onSortSubmitted(SortBy sortBy);

    void onCancelClicked();
}

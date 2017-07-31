package com.udacity.popularMovies.ui.main.sort;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.ui.base.BasePresenter;
import com.udacity.popularMovies.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SortDialogPresenter<V extends SortDialogMvpView> extends BasePresenter<V>
        implements SortDialogMvpPresenter<V> {

    public static final String TAG = "SortDialogPresenter";

    @Inject
    public SortDialogPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSortSubmitted(final SortBy sortBy) {

        // return sortBy to MainActivity

        getMvpView().showLoading();

        //for demo
        getMvpView().hideLoading();
        getMvpView().showMessage("Sorted");

        getMvpView().getInterfaceCommunicator().sendRequestCode(1, sortBy);

        getMvpView().dismissDialog();

    }

    @Override
    public void onCancelClicked() {
        getMvpView().dismissDialog();
    }
}

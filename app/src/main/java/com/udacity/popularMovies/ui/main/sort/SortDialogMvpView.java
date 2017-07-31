package com.udacity.popularMovies.ui.main.sort;

import com.udacity.popularMovies.ui.base.DialogMvpView;

public interface SortDialogMvpView extends DialogMvpView {

    SortDialog.InterfaceCommunicator getInterfaceCommunicator();

    void dismissDialog();
}

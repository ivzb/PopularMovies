package com.udacity.popularMovies.ui.main.sort;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.popularMovies.databinding.DialogSortMoviesBinding;
import com.udacity.popularMovies.di.component.ActivityComponent;
import com.udacity.popularMovies.ui.base.BaseDialog;

import javax.inject.Inject;

public class SortDialog extends BaseDialog implements SortDialogMvpView {

    private static final String TAG = "RateUsDialog";

    public SortDialogListener mSortDialogListener;

    private DialogSortMoviesBinding mBinding;

    @Inject
    SortDialogMvpPresenter<SortDialogMvpView> mPresenter;

    public static SortDialog newInstance() {
        SortDialog fragment = new SortDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (mBinding == null) {
            mBinding = DialogSortMoviesBinding.inflate(inflater, container, false);

            ActivityComponent component = getActivityComponent();

            if (component != null) {
                component.inject(this);

                mPresenter.onAttach(this);
            }
        }

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Verify that the host activity implements the callback interface
        try {
            mSortDialogListener = (SortDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement SortDialogListener");
        }
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    protected void setUp(View view) {
        mBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onSortSubmitted(SortBy.TopRated);
            }
        });
    }

    @Override
    public SortDialogListener getSortDialogListener() {
        return this.mSortDialogListener;
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    public interface SortDialogListener {
        void sendRequestCode(int code, SortBy sortBy);
    }
}
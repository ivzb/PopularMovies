package com.udacity.popularMovies.ui.main.sort;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.udacity.popularMovies.R;
import com.udacity.popularMovies.di.component.ActivityComponent;
import com.udacity.popularMovies.ui.base.BaseDialog;

import javax.inject.Inject;

public class SortDialog extends BaseDialog implements SortDialogMvpView {

    private static final String TAG = "RateUsDialog";

    public InterfaceCommunicator interfaceCommunicator;

    @Inject
    SortDialogMvpPresenter<SortDialogMvpView> mPresenter;

    public static SortDialog newInstance() {
        SortDialog fragment = new SortDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_sort_movies, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {

            component.inject(this);

            mPresenter.onAttach(this);
        }

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    protected void setUp(View view) {

//        mRatingMessageView.setVisibility(View.GONE);
//        mPlayStoreRatingView.setVisibility(View.GONE);
//
//        LayerDrawable stars = (LayerDrawable) mRatingBar.getProgressDrawable();
//        stars.getDrawable(2)
//                .setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow), PorterDuff.Mode.SRC_ATOP);
//        stars.getDrawable(0)
//                .setColorFilter(ContextCompat.getColor(getContext(), R.color.shadow), PorterDuff.Mode.SRC_ATOP);
//        stars.getDrawable(1)
//                .setColorFilter(ContextCompat.getColor(getContext(), R.color.shadow), PorterDuff.Mode.SRC_ATOP);
//
//        mSubmitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.onRatingSubmitted(mRatingBar.getRating(), mMessage.getText().toString());
//            }
//        });

    }

    @Override
    public InterfaceCommunicator getInterfaceCommunicator() {
        return this.interfaceCommunicator;
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

    public interface InterfaceCommunicator {
        void sendRequestCode(int code, SortBy sortBy);
    }
}
package com.udacity.popularMovies.ui.main;

import com.udacity.popularMovies.data.DataManager;
import com.udacity.popularMovies.ui.base.BasePresenter;
import com.udacity.popularMovies.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewInitialized() {
//        getCompositeDisposable()
//            .add(
//                getDataManager()
//                    .getAllQuestions()
//                    .subscribeOn(getSchedulerProvider().io())
//                    .observeOn(getSchedulerProvider().ui())
//                    .subscribe(new Consumer<List<Question>>() {
//                        @Override
//                        public void accept(List<Question> questionList) throws Exception {
//                            if (!isViewAttached()) {
//                                return;
//                            }
//
//                            if (questionList != null) {
//                                getMvpView().refreshQuestionnaire(questionList);
//                            }
//                        }
//                    })
//            );
    }
}

package com.anujtayal.test.ui.presenter;

import com.anujtayal.test.apis.ApiInterface;
import com.anujtayal.test.dagger.GlobalVariables;
import com.anujtayal.test.mvp.BasePresenter;
import com.anujtayal.test.ui.model.StoryResponse;
import com.anujtayal.test.ui.view.StoriesMvpView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class StoriesPresenter extends BasePresenter<StoriesMvpView> {

    @Inject
    @Named(GlobalVariables.Globals.AUTHORISED)
    ApiInterface apiInterface;

    @Inject
    @Named(GlobalVariables.Globals.MAIN_THREAD)
    Scheduler mainThread;

    @Inject
    @Named(GlobalVariables.Globals.NEW_THREAD)
    Scheduler newThread;

    @Inject
    public StoriesPresenter(){
    }

    @Override
    public void attachView(StoriesMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getStories(String section, String apiKey) {
        getMvpView().showProgress();

        apiInterface.getStories(section,apiKey).observeOn(mainThread)
                .subscribeOn(newThread)
                .subscribe(new Observer<StoryResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoryResponse response) {
                        if (getMvpView() != null) {
                            getMvpView().hideProgress();
                            getMvpView().onStoriesResponse(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getMvpView() != null) {
                            getMvpView().hideProgress();
                            getMvpView().showError(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (getMvpView() != null) {
                            getMvpView().hideProgress();
                        }
                    }
                });
    }
}

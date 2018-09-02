package com.anujtayal.test.mvp;

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);
    void detachView();
}
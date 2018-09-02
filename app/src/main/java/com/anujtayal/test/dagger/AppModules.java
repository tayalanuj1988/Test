package com.anujtayal.test.dagger;


import android.app.Application;

import com.anujtayal.test.application.TestApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModules {

    TestApplication testApplication;

    public AppModules(TestApplication application) {
        testApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return testApplication;
    }


    @Provides
    @Singleton
    @Named(GlobalVariables.Globals.MAIN_THREAD)
    Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(GlobalVariables.Globals.NEW_THREAD)
    Scheduler provideNewThreadScheduler() {
        return Schedulers.newThread();
    }
}

package com.anujtayal.test.dagger;

import com.anujtayal.test.application.TestApplication;
import com.anujtayal.test.ui.StoriesActivity;
import com.anujtayal.test.ui.adapter.StoriesAdapter;
import com.anujtayal.test.ui.fragment.StoriesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModules.class, NetworkModule.class})
public interface AppComponents {
    void inject(StoriesActivity storiesActivity);

    void inject(TestApplication context);

    void inject(StoriesFragment storiesFragment);

    void inject(StoriesAdapter storiesAdapter);
}

package com.anujtayal.test.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.anujtayal.test.dagger.AppComponents;
import com.anujtayal.test.dagger.AppModules;
import com.anujtayal.test.dagger.DaggerAppComponents;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class TestApplication extends MultiDexApplication {
    public static AppComponents appComponents;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);
        injectDependencies(this);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    public static void injectDependencies(TestApplication context) {
        appComponents = DaggerAppComponents.builder()
                .appModules(new AppModules(context))
                .build();
        appComponents.inject(context);
    }

    public static AppComponents getAppComponents() {
        return appComponents;
    }

    public static RefWatcher getRefWatcher(Context context) {
        TestApplication application = (TestApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}

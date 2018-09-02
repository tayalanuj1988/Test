package com.anujtayal.test.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.anujtayal.test.R;
import com.anujtayal.test.application.TestApplication;
import com.anujtayal.test.core.BaseActivity;
import com.anujtayal.test.databinding.ActivityMainBinding;
import com.anujtayal.test.ui.fragment.StoriesFragment;

public class StoriesActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frmContainer, StoriesFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependency() {
        TestApplication.getAppComponents().inject(this);
    }
}

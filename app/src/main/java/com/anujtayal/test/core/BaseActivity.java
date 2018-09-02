package com.anujtayal.test.core;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {
    private B dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutID());
        injectDependency();
    }

    public B getViewDataBinding() {
        return dataBinding;
    }

    protected abstract int getLayoutID();

    protected abstract void injectDependency();
}

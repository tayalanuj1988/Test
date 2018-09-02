package com.anujtayal.test.core;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anujtayal.test.dialog.TestDialog;

public abstract class BaseFragment<B extends ViewDataBinding>  extends Fragment {

    private B dataBinding;
    protected TestDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency();
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false);
        if(dataBinding != null ){
            return  dataBinding.getRoot();
        } else {
            return inflater.inflate(getLayoutID(), container, false);
        }
    }

    public B getViewDataBinding() {
        return dataBinding;
    }

    public abstract int getLayoutID();

    public abstract void injectDependency();
}

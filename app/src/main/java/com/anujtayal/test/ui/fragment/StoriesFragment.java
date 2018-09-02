package com.anujtayal.test.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import com.anujtayal.test.R;
import com.anujtayal.test.application.TestApplication;
import com.anujtayal.test.core.BaseFragment;
import com.anujtayal.test.databinding.FragmentStoriesBinding;
import com.anujtayal.test.dialog.TestDialog;
import com.anujtayal.test.ui.adapter.StoriesAdapter;
import com.anujtayal.test.ui.model.StoryResponse;
import com.anujtayal.test.ui.presenter.StoriesPresenter;
import com.anujtayal.test.ui.view.StoriesMvpView;

import java.util.ArrayList;

import javax.inject.Inject;

public class StoriesFragment extends BaseFragment<FragmentStoriesBinding>
        implements StoriesMvpView, OnBackStackChangedListener {
    @Inject
    public StoriesPresenter storiesPresenter;

    StoriesAdapter storiesAdapter;

    public StoriesFragment() {

    }

    public static StoriesFragment newInstance() {
        return new StoriesFragment();
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_stories;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        storiesPresenter.attachView(this);

        storiesAdapter = new StoriesAdapter(getContext(), new ArrayList<>());
        getViewDataBinding().recyclerView.setAdapter(storiesAdapter);
        getViewDataBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getViewDataBinding().spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                storiesPresenter.getStories(adapterView.getItemAtPosition(position).toString()
                                .replaceAll(" ", "").toLowerCase(),
                        getString(R.string.api_key));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void injectDependency() {
        TestApplication.getAppComponents().inject(this);
    }

    @Override
    public void onStoriesResponse(StoryResponse response) {
        storiesAdapter.setStories(response.getResults());
        storiesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        dialog = new TestDialog(getActivity());
        dialog.show();
    }

    @Override
    public void hideProgress() {
        if(dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void onBackStackChanged() {

    }
}

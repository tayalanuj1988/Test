package com.anujtayal.test.ui.view;

import com.anujtayal.test.mvp.MvpView;
import com.anujtayal.test.ui.model.StoryResponse;

public interface StoriesMvpView extends MvpView {
    void onStoriesResponse(StoryResponse response);
}

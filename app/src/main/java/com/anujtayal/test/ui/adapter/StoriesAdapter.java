package com.anujtayal.test.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anujtayal.test.R;
import com.anujtayal.test.application.TestApplication;
import com.anujtayal.test.ui.model.Result;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoryViewHolder> {

    final int ITEM_VIEW_TYPE_STORY = 0;

    List<Result> storiesList;
    Context context;

    public StoriesAdapter(Context context, List<Result> results) {
        this.storiesList = results;
        this.context = context;
        TestApplication.getAppComponents().inject(this);
    }


    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view = LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.layout_story_item, parentViewGroup, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder storyViewHolder, int position) {
        storyViewHolder.tvTitle.setText(storiesList.get(position).getTitle());
        storyViewHolder.tvAbstract.setText(storiesList.get(position).getAbstract());
        if (storiesList.get(position).getMultimedia() != null && storiesList.get(position).getMultimedia().size() > 0) {
            Glide.with(context)
                    .load(storiesList.get(position).getMultimedia().get(0).getUrl())
                    .apply(new RequestOptions().placeholder(R.color.colorAccent)
                            .error(R.color.colorAccent))
                    .into(storyViewHolder.ivThumb);
        }

        storyViewHolder.itemView.setOnClickListener
                (view -> onStoryClicked(storiesList.get(position).getUrl()));
    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_VIEW_TYPE_STORY;
    }

    public void setStories(List<Result> results) {
        this.storiesList = results;
    }

    private void onStoryClicked(String url) {
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        }
    }
}

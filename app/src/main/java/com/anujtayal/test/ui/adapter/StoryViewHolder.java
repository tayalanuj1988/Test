package com.anujtayal.test.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anujtayal.test.R;

public class StoryViewHolder extends RecyclerView.ViewHolder{

    TextView tvTitle;
    TextView tvAbstract;
    ImageView ivThumb;

    public StoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
        tvAbstract = (TextView)itemView.findViewById(R.id.tvAbstract);
        ivThumb = (ImageView)itemView.findViewById(R.id.ivThumb);
    }
}

package com.myapplication.mvvmsample.Demo;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.mvvmsample.R;

public class RecycleViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;

    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.imageview);
    }


}

package com.myapplication.mvvmsample.RecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.mvvmsample.R;

public class TaskRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvTaskName, tvPriority;
    private OnTaskClickListener listener;

    public TaskRecycleViewHolder(@NonNull View itemView, OnTaskClickListener listener) {
        super(itemView);

        this.listener = listener;
        tvTaskName = itemView.findViewById(R.id.task_name);
        tvPriority = itemView.findViewById(R.id.priority);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(listener != null)
        {
            int adapterPosition = getAdapterPosition();

            if(adapterPosition != RecyclerView.NO_POSITION)
            {
                listener.onTaskClick(adapterPosition);
            }

        }

    }
}

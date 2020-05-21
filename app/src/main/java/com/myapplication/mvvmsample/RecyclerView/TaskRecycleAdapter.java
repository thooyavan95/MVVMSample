package com.myapplication.mvvmsample.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.mvvmsample.Database.TaskEntry;
import com.myapplication.mvvmsample.R;
import com.myapplication.mvvmsample.TaskUtil;

import java.util.List;

public class TaskRecycleAdapter extends RecyclerView.Adapter<TaskRecycleViewHolder> {

    private List<TaskEntry> taskEntryList;
    private OnTaskClickListener onTaskClickListener;

    public TaskRecycleAdapter(OnTaskClickListener onTaskClickListener) {
        this.onTaskClickListener = onTaskClickListener;
    }

    @NonNull
    @Override
    public TaskRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_task,parent, false);
        return new TaskRecycleViewHolder(view, onTaskClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRecycleViewHolder holder, int position) {

        holder.tvTaskName.setText(taskEntryList.get(position).getTask());

        int priorityValue = taskEntryList.get(position).getPriority();
        holder.tvPriority.setText(TaskUtil.getPriorityString(priorityValue));

    }

    @Override
    public int getItemCount() {
        if(taskEntryList == null)
        {
            return 0;
        }

        return taskEntryList.size();
    }

    public void setTaskEntryList(List<TaskEntry> taskEntryList) {
        this.taskEntryList = taskEntryList;
        notifyDataSetChanged();
    }

    public List<TaskEntry> getTaskEntryList() {
        return taskEntryList;
    }
}

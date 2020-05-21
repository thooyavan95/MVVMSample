package com.myapplication.mvvmsample.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapplication.mvvmsample.Database.TaskDatabase;
import com.myapplication.mvvmsample.Database.TaskEntry;

public class AddTaskViewModel extends AndroidViewModel {

    private LiveData<TaskEntry> task;

    public AddTaskViewModel(@NonNull Application application, int taskId) {
        super(application);

        TaskDatabase database = TaskDatabase.getInstance(this.getApplication());
        task = database.taskDAO().loadTask(taskId);

    }


    public LiveData<TaskEntry> getTask()
    {
        return task;
    }

}

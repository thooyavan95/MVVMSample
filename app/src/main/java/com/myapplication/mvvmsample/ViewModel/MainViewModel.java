package com.myapplication.mvvmsample.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapplication.mvvmsample.Database.TaskDatabase;
import com.myapplication.mvvmsample.Database.TaskEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<TaskEntry>> listOfTasks;

    public MainViewModel(@NonNull Application application) {
        super(application);

        TaskDatabase database = TaskDatabase.getInstance(this.getApplication());
        listOfTasks = database.taskDAO().loadAllTasks();

    }

    public LiveData<List<TaskEntry>> getAllTask()
    {
        return listOfTasks;
    }

}

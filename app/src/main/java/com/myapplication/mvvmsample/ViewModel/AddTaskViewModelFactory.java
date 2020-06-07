package com.myapplication.mvvmsample.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private int taskId;
    private Application application;

    public AddTaskViewModelFactory(Application application, int taskId)
    {
        this.taskId = taskId;
        this.application = application;
    }


            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

                try {
                    Constructor<T> constructor = modelClass.getConstructor(Application.class, int.class);
                    return constructor.newInstance(application,taskId);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
            return null;
    }
}

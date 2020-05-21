package com.myapplication.mvvmsample.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    private static final String TAG = TaskDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "task_table";
    private static final Object LOCK = new Object();

    private static TaskDatabase sInstance;

    public static TaskDatabase getInstance(Context context)
    {
        if(sInstance == null)
        synchronized (LOCK)
        {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, DATABASE_NAME).build();
        }

        return sInstance;
    }

    public abstract TaskDAO taskDAO();

}

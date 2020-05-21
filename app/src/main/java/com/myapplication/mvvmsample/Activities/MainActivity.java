package com.myapplication.mvvmsample.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myapplication.mvvmsample.AppExecutor;
import com.myapplication.mvvmsample.Database.TaskDatabase;
import com.myapplication.mvvmsample.Database.TaskEntry;
import com.myapplication.mvvmsample.R;
import com.myapplication.mvvmsample.RecyclerView.OnTaskClickListener;
import com.myapplication.mvvmsample.RecyclerView.TaskRecycleAdapter;
import com.myapplication.mvvmsample.ViewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnTaskClickListener {

    private TaskRecycleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();
        setDeleteHelper();

        setUpFabButton();

        showAllTask();

    }

    private void setUpRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        mAdapter = new TaskRecycleAdapter(this);
        recyclerView.setAdapter(mAdapter);

    }


    private void setUpFabButton()
    {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddTaskActivity.class));
            }
        });
    }

    private void showAllTask()
    {
        MainViewModel view = ViewModelProviders.of(this).get(MainViewModel.class);
        view.getAllTask().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(List<TaskEntry> taskEntries) {
                    mAdapter.setTaskEntryList(taskEntries);
            }
        });
    }

    @Override
    public void onTaskClick(int position) {

        int taskId = mAdapter.getTaskEntryList().get(position).getId();

        Intent taskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
        taskIntent.putExtra(AddTaskActivity.EXTRA_TASK, taskId);
        startActivity(taskIntent);

    }

    private void setDeleteHelper()
    {

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {

                AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {

                        int position =  viewHolder.getAdapterPosition();
                        TaskEntry task = mAdapter.getTaskEntryList().get(position);
                        TaskDatabase.getInstance(MainActivity.this).taskDAO().deleteTask(task);
                    }
                });

            }
        }).attachToRecyclerView((RecyclerView)findViewById(R.id.recycler_view));

    }

}

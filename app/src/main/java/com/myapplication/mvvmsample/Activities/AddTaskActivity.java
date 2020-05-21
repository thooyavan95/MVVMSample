package com.myapplication.mvvmsample.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.myapplication.mvvmsample.AppExecutor;
import com.myapplication.mvvmsample.Database.TaskDatabase;
import com.myapplication.mvvmsample.Database.TaskEntry;
import com.myapplication.mvvmsample.R;
import com.myapplication.mvvmsample.TaskUtil;
import com.myapplication.mvvmsample.ViewModel.AddTaskViewModel;
import com.myapplication.mvvmsample.ViewModel.AddTaskViewModelFactory;


public class AddTaskActivity extends AppCompatActivity {

    public static final String EXTRA_TASK = "extra task id";
    private static final int DEFAULT_TASK_ID = -1;

    private int mTaskId = DEFAULT_TASK_ID;

    EditText etTaskName;
    RadioGroup rgPriority;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initViews();
        getExtraIntent();

    }

    private void initViews() {
        etTaskName = findViewById(R.id.et_task_name);
        rgPriority = findViewById(R.id.radiogroup);
        addButton = findViewById(R.id.bt_add_task);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToDB();
            }
        });
    }


    private void addItemToDB() {
        String task = etTaskName.getText().toString().trim();
        int priority = TaskUtil.getPriorityValue(rgPriority);

        final TaskEntry taskEntry = new TaskEntry(task, priority);

        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mTaskId == DEFAULT_TASK_ID) {
                    TaskDatabase.getInstance(AddTaskActivity.this).taskDAO().insertTask(taskEntry);
                } else {
                    taskEntry.setId(mTaskId);
                    TaskDatabase.getInstance(AddTaskActivity.this).taskDAO().updateTask(taskEntry);
                }

                finish();

            }
        });

    }

    private void getExtraIntent() {
        Intent extraIntent = getIntent();
        if (extraIntent.hasExtra(EXTRA_TASK)) {
            mTaskId = extraIntent.getIntExtra(EXTRA_TASK, DEFAULT_TASK_ID);
            getTaskData();
            addButton.setText("Update");
        }
    }

    private void getTaskData() {
        AddTaskViewModelFactory factory = new AddTaskViewModelFactory(getApplication(),mTaskId);

        final AddTaskViewModel addTaskViewModel = ViewModelProviders.of(this, factory).get(AddTaskViewModel.class);

        addTaskViewModel.getTask().observe(this, new Observer<TaskEntry>() {
            @Override
            public void onChanged(TaskEntry taskEntry) {
                addTaskViewModel.getTask().removeObserver(this);
                populateUI(taskEntry);
            }
        });
    }

    private void populateUI(TaskEntry taskEntry) {
        etTaskName.setText(taskEntry.getTask());
        setPriorityInViews(taskEntry.getPriority());
    }

    private void setPriorityInViews(int priority) {
        switch (priority) {
            case TaskUtil.PRIORITY_LOW:
                rgPriority.check(R.id.priority_low);
                break;

            case TaskUtil.PRIORITY_MEDIUM:
                rgPriority.check(R.id.priority_medium);
                break;

            case TaskUtil.PRIORITY_HIGH:
                rgPriority.check(R.id.priority_high);
                break;

        }
    }

}

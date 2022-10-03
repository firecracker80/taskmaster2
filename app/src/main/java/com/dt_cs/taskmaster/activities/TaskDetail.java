package com.dt_cs.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dt_cs.taskmaster.R;
import com.dt_cs.taskmaster.database.TaskDatabase;
import com.dt_cs.taskmaster.models.Task;

import java.util.List;

public class TaskDetail extends AppCompatActivity {
    public static final String DATABASE_NAME = "taskmaster_db";
    TaskDatabase taskDatabase;
    List<Task> tasks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        taskDatabase = Room.databaseBuilder(
                        getApplicationContext(),
                        TaskDatabase.class,
                        DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        tasks = taskDatabase.taskDao().findAll();
        setUpBtns();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tasks.clear();
        tasks.addAll(taskDatabase.taskDao().findAll());

        String taskName = getIntent().getStringExtra("taskName");
        TextView taskNameEdited = findViewById(R.id.TaskDetailTextViewTitle);
        taskNameEdited.setText(taskName);

        String taskBody = getIntent().getStringExtra("taskBody");
        TextView taskBodyEdited = findViewById(R.id.TaskDetailTextViewLorem);
        taskBodyEdited.setText(taskBody);

        String taskStatus = getIntent().getStringExtra("taskStatus");
        TextView taskStatusEdited = findViewById(R.id.TaskDetailStatusTextView);
        taskStatusEdited.setText(taskStatus);
    }

    private void setUpBtns() {
        ImageButton taskDetailsBackBtn = findViewById(R.id.TaskDetailsBackBtn);
        taskDetailsBackBtn.setOnClickListener(view -> {
            Intent goBackToMA = new Intent(TaskDetail.this, MainActivity.class);
            startActivity(goBackToMA);
        });
    }
}
package com.dt_cs.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dt_cs.taskmaster.R;
import com.dt_cs.taskmaster.models.Task;

import java.util.List;

public class TaskDetail extends AppCompatActivity {
    List<Task> tasks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        setUpBtns();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tasks.clear();
        /*tasks.addAll(taskDatabase.taskDao().findAll());*/

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
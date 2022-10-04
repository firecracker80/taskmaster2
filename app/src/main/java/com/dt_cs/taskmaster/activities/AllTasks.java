package com.dt_cs.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageButton;

import com.dt_cs.taskmaster.R;
import com.dt_cs.taskmaster.adapter.TaskListRecyclerViewAdapter;
import com.dt_cs.taskmaster.models.Task;

import java.util.List;

public class AllTasks extends AppCompatActivity {
    List<Task> tasks = null;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        /*tasks = taskDatabase.taskDao().findAll();*/
        setUpBtns();
        setUpTaskRecyclerView();
    }


    private void setUpBtns() {
        ImageButton allTasksBackBtn = findViewById(R.id.AllTaskBackBtn);
        allTasksBackBtn.setOnClickListener(view -> {
            Intent goBackToMA = new Intent(AllTasks.this, MainActivity.class);
            startActivity(goBackToMA);
        });
    }

    private void setUpTaskRecyclerView() {
        RecyclerView taskRecyclerView = findViewById(R.id.TaskRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskRecyclerView.setLayoutManager(layoutManager);

        TaskListRecyclerViewAdapter adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskRecyclerView.setAdapter(adapter);
    }
}
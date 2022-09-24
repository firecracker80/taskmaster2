package com.dt_cs.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dt_cs.taskmaster.adapter.TaskListRecyclerViewAdapter;
import com.dt_cs.taskmaster.database.TaskDatabase;
import com.dt_cs.taskmaster.models.Task;

import java.util.List;

public class TaskDetail extends AppCompatActivity {
    public static final String TASK_NAME_EXTRA_TAG ="taskName";
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
        setUpTaskRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tasks.clear();
        tasks.addAll(taskDatabase.taskDao().findAll());

    }

    private void setUpTaskRecyclerView(){
        RecyclerView taskRecyclerView = findViewById(R.id.TaskRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskRecyclerView.setLayoutManager(layoutManager);

        TaskListRecyclerViewAdapter adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskRecyclerView.setAdapter(adapter);

    }


}
package com.dt_cs.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String TASK_NAME_EXTRA_TAG ="taskName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpTaskBtns();
    }

    private void setUpTaskBtns(){
        Button addTaskBtn = findViewById(R.id.MainActivityAddTaskBtn);
        addTaskBtn.setOnClickListener(view -> {
            Intent goToAddTask = new Intent(MainActivity.this, AddTask.class);
            startActivity(goToAddTask);
        });

        Button allTasksBtn = findViewById(R.id.MainActivityAllTaskBtn);
        allTasksBtn.setOnClickListener(view -> {
            Intent goToAllTasks = new Intent(MainActivity.this, AllTasks.class);
            startActivity(goToAllTasks);
        });

        Button taskDetailBtn = findViewById(R.id.MainActivityTaskBtn);
        taskDetailBtn.setOnClickListener(view -> {
            Intent goToTaskDetail = new Intent(MainActivity.this, TaskDetail.class);
            startActivity(goToTaskDetail);
        });

        Button settingsBtn = findViewById(R.id.MainAcitivtySettingsBtn);
        settingsBtn.setOnClickListener(view -> {
            Intent goToSettings = new Intent(MainActivity.this, Setting.class);
            startActivity(goToSettings);
        });

//    @Override
//    protected void onResume(){
//        super.onResume();
//        String userName = sharedPreferences.getString(Setting.USER_NAME_TAG, "No username");
//            TextView userNameEdited = findViewById(R.id.MainActivityTextViewWelcome);
//            userNameEdited.setText("Welcome to " + userName + "'s Tasks");
//        }
//    }
}
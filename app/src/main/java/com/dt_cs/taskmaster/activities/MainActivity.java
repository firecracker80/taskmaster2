package com.dt_cs.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.dt_cs.taskmaster.R;
import com.dt_cs.taskmaster.adapter.TaskListRecyclerViewAdapter;
import com.dt_cs.taskmaster.models.Task;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String TASK_NAME_EXTRA_TAG ="taskName";
    public static final String TASK_BODY_EXTRA_TAG = "taskBody";
    public static final String TASK_STATUS_EXTRA_TAG = "taskStatus";
    List<Task> tasks = new ArrayList<>();
    TaskListRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

       /* tasks = taskDatabase.taskDao().findAll();*/

        onResume();
        setUpTaskBtns();
        setUpUserProfileImage();
        setUpTaskRecyclerView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        /*tasks.clear();*/

        String userName = "userName";
            if(sharedPreferences != null){
                userName = sharedPreferences.getString(Setting.USER_NAME_TAG, "userName");
            }
        TextView userNameEdited = findViewById(R.id.MainActivityTextViewWelcome);
            userNameEdited.setText("Welcome to " + userName + "'s Tasks");

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

        Button settingsBtn = findViewById(R.id.MainActivtySettingsBtn);
        settingsBtn.setOnClickListener(view -> {
            Intent goToSettings = new Intent(MainActivity.this, Setting.class);
            startActivity(goToSettings);
        });
        Button profileBtn = findViewById(R.id.UserProfileBtn);
        profileBtn.setOnClickListener(view -> {
            Intent goToProfile = new Intent(MainActivity.this, UserProfile.class);
            startActivity(goToProfile);
        });


    }

    private void setUpUserProfileImage(){
        ImageView userImage = findViewById(R.id.mainActivityUserProfileImageView);
        userImage.setOnClickListener(view -> {
            Intent goToUserProfile = new Intent(MainActivity.this, UserProfile.class);
            startActivity(goToUserProfile);
        });
    }


    private void setUpTaskRecyclerView(){
            RecyclerView taskRecyclerView = findViewById(R.id.TaskRecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            taskRecyclerView.setLayoutManager(layoutManager);

            adapter = new TaskListRecyclerViewAdapter(tasks, this);
            taskRecyclerView.setAdapter(adapter);
    }
}
package com.dt_cs.taskmaster;

import static com.dt_cs.taskmaster.models.Status.inProgress;
import static com.dt_cs.taskmaster.models.Status.newTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.dt_cs.taskmaster.adapter.TaskListRecyclerViewAdapter;
import com.dt_cs.taskmaster.database.TaskDatabase;
import com.dt_cs.taskmaster.models.Task;
import com.dt_cs.taskmaster.dao.TaskDao;



public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String TASK_NAME_EXTRA_TAG ="taskName";
    public static final String DATABASE_NAME = "taskmaster_db";
    TaskDatabase taskDatabase;
    List<Task> tasks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        taskDatabase = Room.databaseBuilder(
                getApplicationContext(),
                TaskDatabase.class,
                DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        List<Task> tasks;
        tasks = taskDatabase.taskDao().findAll();

        onResume();
        setUpTaskBtns();
        setUpUserProfileImage();
        setUpTaskRecyclerView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        tasks.clear();
        tasks.addAll(taskDatabase.taskDao().findAll());

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


//            tasks.add(new Task("Light bill", "Pay light bill by Friday.", newTask));
//            tasks.add(new Task("Groceries", "Make a list.", newTask));
//            tasks.add(new Task("Read homework", "Read articles for homework by tomorrow", inProgress));

            TaskListRecyclerViewAdapter adapter = new TaskListRecyclerViewAdapter(tasks, this);
            taskRecyclerView.setAdapter(adapter);
    }
}
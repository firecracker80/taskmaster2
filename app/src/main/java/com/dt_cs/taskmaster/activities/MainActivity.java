package com.dt_cs.taskmaster.activities;

import static com.dt_cs.taskmaster.activities.AddTask.Tag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.dt_cs.taskmaster.R;
import com.dt_cs.taskmaster.adapter.TaskListRecyclerViewAdapter;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String TASK_NAME_EXTRA_TAG ="taskName";
    public static final String TASK_BODY_EXTRA_TAG = "taskBody";
    public static final String TASK_STATUS_EXTRA_TAG = "taskStatus";
    public static final String TAG = "MainActivity";
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

        Team teamHouse = Team.builder()
                .name("House")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(teamHouse),
                success -> Log.i(TAG, "Team House built"),
                failure -> Log.i(TAG, "Team House not built")
                );

        Team teamWork = Team.builder()
                .name("Work")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(teamWork),
                success -> Log.i(TAG, "Team Work built"),
                failure -> Log.i(TAG, "Team Work not built")
        );

        Team teamSchool = Team.builder()
                .name("School")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(teamSchool),
                success -> Log.i(TAG, "Team School built"),
                failure -> Log.i(TAG, "Team School not built")
        );
    }

    @Override
    protected void onResume(){
        super.onResume();

        String userName = "userName";
            if(sharedPreferences != null){
                userName = sharedPreferences.getString(Setting.USER_NAME_TAG, "userName");
            }
        TextView userNameEdited = findViewById(R.id.MainActivityTextViewWelcome);
            userNameEdited.setText("Welcome to " + userName + "'s Tasks");

        Amplify.API.query(
                ModelQuery.list(Task.class),
                successResponse -> {
                    Log.i(Tag, "Read AddTask successfully!");
                    tasks.clear();
                    for (Task dataTask : successResponse.getData()){
                        tasks.add(dataTask);
                    }
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                },
                failureResponse -> Log.i(Tag, "Did not read AddTask successfully")
        );


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
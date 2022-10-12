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
import android.widget.ImageButton;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.dt_cs.taskmaster.R;
import com.dt_cs.taskmaster.adapter.TaskListRecyclerViewAdapter;

import java.util.List;

public class AllTasks extends AppCompatActivity {
    List<Task> tasks = null;
    TaskListRecyclerViewAdapter adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

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

        adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskRecyclerView.setAdapter(adapter);
    }
}
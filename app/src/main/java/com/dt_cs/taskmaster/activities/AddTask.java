package com.dt_cs.taskmaster.activities;

import static com.amplifyframework.datastore.generated.model.Task.*;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Status;
import com.amplifyframework.datastore.generated.model.Task;
import com.dt_cs.taskmaster.R;

import java.util.Date;

public class AddTask extends AppCompatActivity {
    public static final String Tag = "AddTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        setUpTypeSpinner();
        setUpSubmitBtn();
        setUpAddTaskBackBtns();
    }


    private void setUpTypeSpinner(){
        Spinner taskTypeSpinner = findViewById(R.id.AddTaskTypeSpinner);
        taskTypeSpinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                Status.values()
        ));
    }

    private void setUpSubmitBtn(){
        Spinner taskTypeSpinner = findViewById(R.id.AddTaskTypeSpinner);
        Button savedNewTaskBtn = findViewById(R.id.AddTaskSubmitBtn);
        savedNewTaskBtn.setOnClickListener(view -> {
            String taskTitle = ((EditText)findViewById(R.id.AddTaskTitleET)).getText().toString();
            String taskDescription = ((EditText)findViewById(R.id.AddTaskDescriptionET)).getText().toString();
            String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());

           Task newTasks = Task.builder()
                   .title(taskTitle)
                   .body(taskDescription)
                   .status((Status) taskTypeSpinner.getSelectedItem())
                   .dateCreated(new Temporal.DateTime(currentDateString))
                   .build();

           Amplify.API.mutate(
                   ModelMutation.create(newTasks),
                   successResponse -> Log.i(Tag, "AddTask: created a Task successfully"),
                   failureResponse -> Log.i(Tag, "AddTask: failed to create Task" + failureResponse)
           );
            Intent goToAllTasks  = new Intent(AddTask.this, AllTasks.class);
            startActivity((goToAllTasks));
        });

    }

    private void setUpAddTaskBackBtns() {
        ImageButton addTasksBackBtn = findViewById(R.id.AddTaskBackBtn);
        addTasksBackBtn.setOnClickListener(view -> {
            Intent goBackToMA = new Intent(AddTask.this, MainActivity.class);
            startActivity(goBackToMA);
        });
    }
}
package com.dt_cs.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.dt_cs.taskmaster.R;
import com.dt_cs.taskmaster.database.TaskDatabase;
import com.dt_cs.taskmaster.models.Task;

import java.util.Date;

public class AddTask extends AppCompatActivity {
    public static final String DATABASE_NAME = "taskmaster_db";
    TaskDatabase taskDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskDatabase = Room.databaseBuilder(
                getApplicationContext(),
                TaskDatabase.class,
                DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();

//        setUpTaskTitle();
        setUpTypeSpinner();
        setUpSubmitBtn();
        setUpAddTaskBackBtns();
    }


    private void setUpTypeSpinner(){
        Spinner taskTypeSpinner = findViewById(R.id.AddTaskTypeSpinner);
        taskTypeSpinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                Task.Status.values()
        ));
    }

    private void setUpSubmitBtn(){
        Spinner taskTypeSpinner = findViewById(R.id.AddTaskTypeSpinner);
        Button savedNewTaskBtn = findViewById(R.id.AddTaskSubmitBtn);
        savedNewTaskBtn.setOnClickListener(view -> {
            String taskTitle = ((EditText)findViewById(R.id.AddTaskTitleET)).getText().toString();
            String taskDescription = ((EditText)findViewById(R.id.AddTaskDescriptionET)).getText().toString();

            java.util.Date newDate = new Date();
            Task.Status status = Task.Status.fromString(taskTypeSpinner.getSelectedItem().toString());

            Task newTask = new Task(taskTitle, taskDescription, status, newDate);
            taskDatabase.taskDao().insertTask(newTask);
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
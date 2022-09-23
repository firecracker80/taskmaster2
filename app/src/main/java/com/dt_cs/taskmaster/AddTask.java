package com.dt_cs.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    private void setUpTaskTitle(){
        Intent callingIntent = getIntent();
        String taskTitleString = "";
        if (callingIntent != null){
            taskTitleString = ((Intent) callingIntent).getStringExtra(TaskDetail.TASK_NAME_EXTRA_TAG);
            TextView addTaskTitle = findViewById(R.id.TaskDetailTextViewTitle);
            addTaskTitle.setText(taskTitleString);
        }
    }
}
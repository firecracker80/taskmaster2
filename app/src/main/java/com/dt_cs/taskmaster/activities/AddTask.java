package com.dt_cs.taskmaster.activities;


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
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Status;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.dt_cs.taskmaster.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTask extends AppCompatActivity {
    public static final String Tag = "AddTask";
    Spinner teamSpinner = null;
    CompletableFuture<List<Team>> teamFuture = null;
    Team selectedTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        setUpTypeSpinner();
        setUpSubmitBtn();
        setUpAddTaskBackBtn();
        setUpTeamSpinner();
    }

    private void setUpTeamSpinner(){
        teamFuture = new CompletableFuture<>();

        Amplify.API.query(
                ModelQuery.list(Team.class),
                success -> {
                    Log.i(Tag, "Read teams successfully");
                    ArrayList<String> teamNames = new ArrayList<>();
                    ArrayList<Team> teams = new ArrayList<>();
                    for (Team team : success.getData()){
                        teams.add(team);
                        teamNames.add(team.getName());
                    }
                    teamFuture.complete(teams);
                    runOnUiThread(() -> {
                        teamSpinner.setAdapter(new ArrayAdapter<>(
                                this,
                                android.R.layout.simple_spinner_item,
                                teamNames
                        ));
                    });
                },
                failure -> {
                    teamFuture.complete(null);
                    Log.i(Tag, "Did not read teams successfully");
                }
        );
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

        Spinner taskStateSpinner = findViewById(R.id.AddTaskTypeSpinner);
        Button addTaskSubmitButton = findViewById(R.id.AddTaskSubmitBtn);
        Spinner teamSpinner = findViewById(R.id.AddTaskTeamSpinner);

        addTaskSubmitButton.setOnClickListener((view -> {

            String selectedTeamString = teamSpinner.getSelectedItem().toString();
            List<Team> teams = null;
            try {
                teams = teamFuture.get();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                Thread.currentThread().interrupt();
            } catch (ExecutionException ee) {
                ee.printStackTrace();
            }

            selectedTeam = teams.stream().filter(t -> t.getName().equals(selectedTeamString)).findAny().orElseThrow(RuntimeException::new);

        Spinner taskTypeSpinner = findViewById(R.id.AddTaskTypeSpinner);
        Button savedNewTaskBtn = findViewById(R.id.AddTaskSubmitBtn);
        savedNewTaskBtn.setOnClickListener((view1 -> {
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
            }));

        }));
    }

    private void setUpAddTaskBackBtn() {
        ImageButton addTasksBackBtn = findViewById(R.id.AddTaskBackBtn);
        addTasksBackBtn.setOnClickListener((view -> {
            Intent goBackToMA = new Intent(AddTask.this, MainActivity.class);
            startActivity(goBackToMA);
            }));
        }
}

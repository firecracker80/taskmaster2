package com.dt_cs.taskmaster.activities;

import static com.dt_cs.taskmaster.activities.AddTask.Tag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.dt_cs.taskmaster.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Setting extends AppCompatActivity{
    public static final String USER_NAME_TAG = "userName";
    public static final String USER_TEAM_TAG = "userTeam";
    SharedPreferences sharedPreferences;
    Spinner teamSpinner = null;
    CompletableFuture<List<Team>> teamFuture = null;
    List<String> teamNames = null;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_setting);

        onResume();
        setUpSaveBtn();
        setUpBtns();
        setUpTeamSpinner();
    }

    @Override
    protected void onResume(){
        super.onResume();
        String userName = sharedPreferences.getString(UserProfile.USER_NAME_TAG, "");
        TextView userNameEdited = findViewById(R.id.SettingsXmlPlainTextUsername);
        userNameEdited.setText(userName);
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

    private void setUpSaveBtn (){
        Button saveBtn = findViewById(R.id.SettingXmlSaveBtn);
        Spinner teamSpinner = findViewById(R.id.SettingTeamSpinner);

        saveBtn.setOnClickListener(view -> {
            SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();
            String nameInput = ((EditText) findViewById(R.id.SettingsXmlPlainTextUsername)).getText().toString();
            preferenceEditor.putString(USER_NAME_TAG, nameInput);
            String teamChoice = ((String) teamSpinner.getSelectedItem());
            preferenceEditor.putString(USER_TEAM_TAG, teamChoice);
            preferenceEditor.apply();
            Intent goToSavedSettings = new Intent(Setting.this, Setting.class);
            startActivity(goToSavedSettings);

            Toast.makeText(Setting.this, "Settings Saved", Toast.LENGTH_SHORT).show();
        });
    }

    private void setUpBtns() {
        ImageButton settingsBackBtn = findViewById(R.id.SettingsBackBtn);
        settingsBackBtn.setOnClickListener(view -> {
            Intent goBackToMA = new Intent(Setting.this, MainActivity.class);
            startActivity(goBackToMA);
        });
    }

    private void setUpTeamSpinner(){
        Spinner teamSpinner = findViewById(R.id.SettingTeamSpinner);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, teamNames);
        teamSpinner.setAdapter(adapter);
    }
}
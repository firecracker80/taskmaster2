package com.dt_cs.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dt_cs.taskmaster.R;

public class Setting extends AppCompatActivity{
    public static final String USER_NAME_TAG = "userName";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_setting);

        onResume();
        setUpSaveBtn();
        setUpBtns();
    }

    @Override
    protected void onResume(){
        super.onResume();
        String userName = sharedPreferences.getString(UserProfile.USER_NAME_TAG, "");
        TextView userNameEdited = findViewById(R.id.SettingsXmlPlainTextUsername);
        userNameEdited.setText(userName);
    }

    private void setUpSaveBtn (){
        Button saveBtn = findViewById(R.id.SettingXmlSaveBtn);
        saveBtn.setOnClickListener(view -> {
            SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();
            String nameInput = ((EditText) findViewById(R.id.SettingsXmlPlainTextUsername)).getText().toString();
            preferenceEditor.putString(USER_NAME_TAG, nameInput);
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
}
package com.dt_cs.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dt_cs.taskmaster.R;

public class UserProfile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String USER_NAME_TAG = "userName";
    public static final String USER_EMAIL_TAG = "userEmail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString(USER_NAME_TAG, "");
        if(!userName.isEmpty()){
            EditText userNameEdited = findViewById(R.id.SettingsXmlPlainTextUsername);
            userNameEdited.setText(userName);
        }
        setUpBtns();
        setUpSubmitButton();
    }



    private void setUpSubmitButton(){
        Button submitButton = findViewById(R.id.UserProfileSubmitBtn);
        submitButton.setOnClickListener(view -> {
            SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();
            String nameInput = ((EditText) findViewById(R.id.UserProfileETPersonName)).getText().toString();
            String emailInput = ((EditText) findViewById(R.id.UserProfileETEmail)).getText().toString();
            preferenceEditor.putString(USER_NAME_TAG, nameInput);
            preferenceEditor.putString(USER_EMAIL_TAG, emailInput);
            preferenceEditor.apply();

            Toast.makeText(UserProfile.this, "Profile saved", Toast.LENGTH_SHORT).show();
        });
    }

    private void setUpBtns() {
        ImageButton userProfileBackBtn = findViewById(R.id.UserProfileBackBtn);
        userProfileBackBtn.setOnClickListener(view -> {
            Intent goBackToMA = new Intent(UserProfile.this, MainActivity.class);
            startActivity(goBackToMA);
        });
    }


}
package com.dt_cs.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class UserProfile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String USER_NAME_TAG = "userName";


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
    }


}
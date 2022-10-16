package com.dt_cs.taskmaster.activities;

import static com.dt_cs.taskmaster.activities.AddTask.Tag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.dt_cs.taskmaster.R;

public class Login extends AppCompatActivity {
    public static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpLoginForm();
    }

    public void setUpLoginForm(){
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(Verify.VERIFY_ACCOUNT_EMAIL_TAG);
        findViewById(R.id.loginSubmitBtn).setOnClickListener(view -> {
            String userPassword = ((EditText) findViewById(R.id.loginPasswordEditText)).getText().toString();

            Amplify.Auth.signIn(
                    userEmail,
                    userPassword,
                    success -> {Log.i(Tag,"Login succeeded: " + success);
                    Intent goToMainActivity = new Intent(Login.this, MainActivity.class);
                    startActivity(goToMainActivity);
                    },

                    failure -> {
                        Log.i(Tag, "Login failed: " + failure);
                        runOnUiThread(() -> {
                            Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show();
                        });
                    }
            );
        });
    }
}
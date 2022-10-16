package com.dt_cs.taskmaster.activities;

import static com.amazonaws.services.cognitoidentityprovider.model.EventType.SignUp;
import static com.dt_cs.taskmaster.activities.AddTask.Tag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.dt_cs.taskmaster.R;

public class Signup extends AppCompatActivity {
    public static final String SIGNUP_EMAIL_TAG = "Signup_Email_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    private void setUpSignUpForm(){

        findViewById(R.id.signUpSubmitButton).setOnClickListener(view -> {
            String userEmail = ((EditText)findViewById(R.id.signupEmailEditText)).getText().toString();
            String userPassword = ((EditText) findViewById(R.id.signupPasswordEditText)).getText().toString();
            Amplify.Auth.signUp(userEmail,
                    userPassword,
                    AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.email(), userEmail)
                            .build(),
                    success -> {
                        Log.i(Tag, "Signup successful! " + success);
                        Intent goToVerify = new Intent(Signup.this, Verify.class);
                        goToVerify.putExtra(SIGNUP_EMAIL_TAG, userEmail);
                        startActivity(goToVerify);
                    },
                    failure -> {
                        Log.i(Tag, "Signup failed with email " + userEmail + " with message: " + failure);

                        runOnUiThread(() -> Toast.makeText(Signup.this, "Signup Failed", Toast.LENGTH_SHORT).show());
                    }
            );
        });
    }
}
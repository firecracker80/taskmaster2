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

public class Verify extends AppCompatActivity {
    public static final String VERIFY_ACCOUNT_EMAIL_TAG = "Verify_Account_Email_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
    }

    public void setUpVerificationForm() {
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(Signup.SIGNUP_EMAIL_TAG);
        findViewById(R.id.verifySubmitButton).setOnClickListener(view -> {
            String verificationCode = ((EditText) findViewById(R.id.verifyEditTextNumber)).getText().toString();

            Amplify.Auth.confirmSignUp(
                    userEmail,
                    verificationCode,
                    success -> {
                        Log.i(Tag, "Verification succeeded: " + success.toString());
                        Intent goToLoginPage = new Intent(Verify.this, Login.class);
                        goToLoginPage.putExtra(VERIFY_ACCOUNT_EMAIL_TAG, userEmail);
                        startActivity(goToLoginPage);
                    },
                    failure -> {
                        Log.i(Tag, "Verification failed with username: " + userEmail + " with this message: " + failure.toString());
                        runOnUiThread(() -> {
                            Toast.makeText(Verify.this, "Verify account failed", Toast.LENGTH_SHORT).show();
                        });
                    }
            );
        });

        Amplify.Auth.confirmSignUp("yari@yvelazquez.com", "606164",
                success -> Log.i(Tag, "Verification succeeded: " + success),
                failure -> Log.i(Tag, "Verification failed: " + failure)
        );
    }
}
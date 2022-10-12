package com.dt_cs.taskmaster;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;

public class TaskAmplifyApp extends Application {
    public static final String Tag = "TaskApp";

    @Override
    public void onCreate(){
        super.onCreate();
        try{
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
        } catch(AmplifyException ae){
            Log.e(Tag, "Error initializing Amplify: " + ae.getMessage(), ae);
        }
    }
}

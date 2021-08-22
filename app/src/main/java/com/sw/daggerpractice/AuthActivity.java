package com.sw.daggerpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";
    @Inject
    String someString;
    @Inject
    boolean isAppNull;
    @Inject
    int someint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Log.d(TAG, "onCreate: " + someString);
        Log.d(TAG, "onCreate: " + someint);
        Log.d(TAG, "onCreate: is app null?  " + isAppNull);
    }

}
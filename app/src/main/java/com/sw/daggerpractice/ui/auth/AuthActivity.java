package com.sw.daggerpractice.ui.auth;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.sw.daggerpractice.R;
import com.sw.daggerpractice.models.User;
import com.sw.daggerpractice.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";


    private AuthViewModel viewModel;

    private EditText userId;
    private ProgressBar progressBar;
    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;
    @Inject
    RequestManager requestManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.login_button).setOnClickListener(this);
        viewModel = new ViewModelProvider(this, providerFactory).get(AuthViewModel.class);
        // viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);

        setLogo();
        subscribeObserver();
    }

    private void subscribeObserver() {
        viewModel.observUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING:
                            showProgresBar(true);
                            break;
                        case AUTHENTICATED:
                            showProgresBar(false);
                            Log.d(TAG, "onChanged: AUTHENTICATED :  " + userAuthResource.data.getEmail());
                            break;
                        case ERROR:
                            showProgresBar(false);
                            Toast.makeText(AuthActivity.this, userAuthResource.message
                                    +"\n did you enter user id betwen 1 and 10 ", Toast.LENGTH_SHORT).show();
                            break;
                        case NOT_AUTHENTICATED:
                            showProgresBar(false);
                            break;
                    }
                }
            }
        });
    }

    private void showProgresBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo() {
        requestManager.load(logo).
                into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                usetLogin();
                break;
        }
    }

    private void usetLogin() {
        if (TextUtils.isEmpty(userId.getText().toString())) {
            return;
        }
        viewModel.authWithId(Integer.valueOf(userId.getText().toString()));
    }
}
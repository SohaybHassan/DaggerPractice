package com.sw.daggerpractice.ui.auth;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
        findViewById(R.id.login_button).setOnClickListener(this);
        viewModel = new ViewModelProvider(this, providerFactory).get(AuthViewModel.class);
        // viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);

        setLogo();
        subscribeObserver();
    }

    private void subscribeObserver() {
        viewModel.observUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    Log.d(TAG, "onChanged: " + user.getEmail());
                }else{
                    Log.d(TAG, "onChanged:  user is null");
                }
            }
        });
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
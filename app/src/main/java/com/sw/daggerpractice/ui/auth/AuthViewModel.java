package com.sw.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.sw.daggerpractice.di.auth.AuthModule;
import com.sw.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {


    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: view Molde working done ! ^_^ ");
        if (authApi==null){
            Log.d(TAG, "AuthViewModel: AuthApi is null");
        }else{
            Log.d(TAG, "AuthViewModel:  Auth Api is not null");
        }
    }
}

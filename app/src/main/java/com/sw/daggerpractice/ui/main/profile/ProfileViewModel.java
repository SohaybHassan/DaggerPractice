package com.sw.daggerpractice.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sw.daggerpractice.SessionManger;
import com.sw.daggerpractice.models.User;
import com.sw.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "profileViewModel";

    private final SessionManger sessionManger;

    @Inject
    public ProfileViewModel(SessionManger sessionManger) {
        this.sessionManger = sessionManger;

        Log.d(TAG, "profileViewModel:  view Model is ready to work ...!");
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return sessionManger.getAuthUser();
    }
}

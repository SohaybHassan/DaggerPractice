package com.sw.daggerpractice.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "profileViewModel";

    @Inject
    public ProfileViewModel() {

        Log.d(TAG, "profileViewModel:  view Model is ready to work ...!");
    }
}

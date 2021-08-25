package com.sw.daggerpractice.ui.auth;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.sw.daggerpractice.di.auth.AuthModule;
import com.sw.daggerpractice.models.User;
import com.sw.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {


    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private MediatorLiveData<User> authUser =   new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: view Molde working done ! ^_^ ");
    }


    public void authWithId(int id){
        final LiveData<User> source= LiveDataReactiveStreams.fromPublisher(
                authApi.getUsers(id).subscribeOn(Schedulers.io())
        );
        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });

    }
    public LiveData<User> observUser(){
        return authUser;
    }
}

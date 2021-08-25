package com.sw.daggerpractice.ui.auth;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.sw.daggerpractice.SessionManger;
import com.sw.daggerpractice.di.auth.AuthModule;
import com.sw.daggerpractice.models.User;
import com.sw.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {


    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private SessionManger sessionManger;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManger sessionManger) {
        this.authApi = authApi;
        this.sessionManger = sessionManger;
        Log.d(TAG, "AuthViewModel: view Molde working done ! ^_^ ");
    }


    public void authWithId(int id) {
        Log.d(TAG, "authWithId: attempting to login");
        sessionManger.authWithId(queryUserId(id));
    }

    private LiveData<AuthResource<User>> queryUserId(int id) {
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUsers(id)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(@NonNull Throwable throwable) throws Exception {
                                User userError = new User();
                                userError.setId(-1);
                                return userError;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(@NonNull User user) throws Exception {
                                if (user.getId() == -1) {
                                    return AuthResource.error("Could not Authenticate", null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<AuthResource<User>> observAuthState() {
        return sessionManger.getAuthUser();
    }
}

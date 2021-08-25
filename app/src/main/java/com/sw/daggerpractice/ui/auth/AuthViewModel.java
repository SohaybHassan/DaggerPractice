package com.sw.daggerpractice.ui.auth;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.sw.daggerpractice.di.auth.AuthModule;
import com.sw.daggerpractice.models.User;
import com.sw.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {


    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: view Molde working done ! ^_^ ");
       authApi.getUsers(1)
               .toObservable()
               .subscribeOn(Schedulers.io())
               .subscribe(new Observer<User>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onNext(@NonNull User user) {
                       Log.d(TAG, "onNext:  "+user.getEmail());
                   }

                   @Override
                   public void onError(@NonNull Throwable e) {
                       Log.d(TAG, "onError: "+e.getMessage());
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }
}

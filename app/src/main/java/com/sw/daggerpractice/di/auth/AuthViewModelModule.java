package com.sw.daggerpractice.di.auth;

import androidx.lifecycle.ViewModel;

import com.sw.daggerpractice.di.ViewModelKey;
import com.sw.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);

}

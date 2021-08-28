package com.sw.daggerpractice.di.main;

import androidx.lifecycle.ViewModel;

import com.sw.daggerpractice.di.ViewModelKey;
import com.sw.daggerpractice.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);
}

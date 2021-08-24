package com.sw.daggerpractice.di;

import androidx.lifecycle.ViewModelProvider;

import com.sw.daggerpractice.viewmodel.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory (ViewModelProviderFactory viewModelProviderFactory);
}

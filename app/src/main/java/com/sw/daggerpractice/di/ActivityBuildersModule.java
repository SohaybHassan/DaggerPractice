package com.sw.daggerpractice.di;

import com.sw.daggerpractice.di.auth.AuthModule;
import com.sw.daggerpractice.di.auth.AuthViewModelModule;
import com.sw.daggerpractice.di.main.MainFragmentBuildersModule;
import com.sw.daggerpractice.ui.auth.AuthActivity;
import com.sw.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
            modules = {AuthViewModelModule.class, AuthModule.class}
    )
    abstract AuthActivity ContributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class}
    )
    abstract MainActivity ContributeMainActivity();
}

package com.sw.daggerpractice.di;

import com.sw.daggerpractice.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract AuthActivity ContributeAuthActivity();

    @Provides
    static String someString() {
        return "sohiab Hassan";
    }
}

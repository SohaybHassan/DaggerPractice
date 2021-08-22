package com.sw.daggerpractice;

import com.sw.daggerpractice.di.DaggerAppComponent;

import dagger.android.support.DaggerApplication;


public class BaseApplication extends DaggerApplication {

    @Override
    protected dagger.android.AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}

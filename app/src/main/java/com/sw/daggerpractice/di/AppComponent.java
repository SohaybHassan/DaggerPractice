package com.sw.daggerpractice.di;

import android.app.Application;

import com.sw.daggerpractice.BaseApp;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class
})
public interface AppComponent extends AndroidInjector<BaseApp> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}


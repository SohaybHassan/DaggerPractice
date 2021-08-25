package com.sw.daggerpractice.di;

import android.app.Application;

import com.sw.daggerpractice.BaseApplication;
import com.sw.daggerpractice.SessionManger;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class, ViewModelFactoryModule.class
})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManger sessionManger();
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}


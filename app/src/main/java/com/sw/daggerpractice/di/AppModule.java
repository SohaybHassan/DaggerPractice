package com.sw.daggerpractice.di;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    static String someString() {
        return "sohiab Hassan";
    }

    @Singleton
    @Provides
    static boolean getApp(Application application) {
        return application != null;
    }

    @Singleton
    @Provides
    static int someInt(String s) {
        if (s.equals("sohiab Hassan")) {
            return 1;
        }
        return 0;
    }
}

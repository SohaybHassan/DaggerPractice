package com.sw.daggerpractice.di.main;

import com.sw.daggerpractice.network.main.MinaAPi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MinaAPi provideMinaPai(Retrofit retrofit) {
        return retrofit.create(MinaAPi.class);
    }
}

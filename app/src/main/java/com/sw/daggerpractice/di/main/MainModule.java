package com.sw.daggerpractice.di.main;

import com.google.android.material.transition.SlideDistanceProvider;
import com.sw.daggerpractice.network.main.MinaAPi;
import com.sw.daggerpractice.ui.main.post.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {


    @Provides
    static PostsRecyclerAdapter proideAdapter() {
        return new PostsRecyclerAdapter();
    }

    @Provides
    static MinaAPi provideMinaPai(Retrofit retrofit) {
        return retrofit.create(MinaAPi.class);
    }
}

package com.sw.daggerpractice.di.main;


import com.sw.daggerpractice.ui.main.post.PostFragment;
import com.sw.daggerpractice.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributePostFragment();

}

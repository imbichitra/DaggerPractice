package com.bichi.daggerpractice.di.main;

import com.bichi.daggerpractice.ui.main.posts.PostsFragments;
import com.bichi.daggerpractice.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ProfileFragment contributesProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragments contributesPostsFragments();
}

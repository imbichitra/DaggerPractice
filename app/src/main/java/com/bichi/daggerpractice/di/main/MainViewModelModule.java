package com.bichi.daggerpractice.di.main;

import androidx.lifecycle.ViewModel;

import com.bichi.daggerpractice.di.ViewModelKey;
import com.bichi.daggerpractice.ui.main.posts.PostsFragments;
import com.bichi.daggerpractice.ui.main.posts.PostsViewModel;
import com.bichi.daggerpractice.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);
}

package com.bichi.daggerpractice.di.auth;

import androidx.lifecycle.ViewModel;

import com.bichi.daggerpractice.di.ViewModelKey;
import com.bichi.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}

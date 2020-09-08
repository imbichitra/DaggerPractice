package com.bichi.daggerpractice.di;

import androidx.lifecycle.ViewModelProvider;

import com.bichi.daggerpractice.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);
}

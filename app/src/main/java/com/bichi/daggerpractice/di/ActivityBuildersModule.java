package com.bichi.daggerpractice.di;

import com.bichi.daggerpractice.di.auth.AuthModule;
import com.bichi.daggerpractice.di.auth.AuthScope;
import com.bichi.daggerpractice.di.auth.AuthViewModelsModule;
import com.bichi.daggerpractice.di.main.MainFragmentBuildersModule;
import com.bichi.daggerpractice.di.main.MainModule;
import com.bichi.daggerpractice.di.main.MainScope;
import com.bichi.daggerpractice.di.main.MainViewModelModule;
import com.bichi.daggerpractice.ui.auth.AuthActivity;
import com.bichi.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();
}

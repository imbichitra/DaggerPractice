package com.bichi.daggerpractice.ui.main.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bichi.daggerpractice.SessionManager;
import com.bichi.daggerpractice.models.User;
import com.bichi.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private final SessionManager sessionManager;
    @Inject
    public ProfileViewModel(SessionManager sessionManager){
        this.sessionManager = sessionManager;
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }
}

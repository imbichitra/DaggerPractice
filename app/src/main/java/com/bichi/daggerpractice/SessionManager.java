package com.bichi.daggerpractice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.bichi.daggerpractice.models.User;
import com.bichi.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    public static final String TAG = SessionManager.class.getSimpleName();

    private MediatorLiveData<AuthResource<User>> cacheUser = new MediatorLiveData<>();

    @Inject
    public SessionManager(){}

    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        if (cacheUser != null){
            cacheUser.setValue(AuthResource.loading((User)null));
            cacheUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cacheUser.setValue(userAuthResource);
                    cacheUser.removeSource(source);
                }
            });
        }
    }

    public void logOut(){
        cacheUser.setValue(AuthResource.<User>logout());
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return cacheUser;
    }
}

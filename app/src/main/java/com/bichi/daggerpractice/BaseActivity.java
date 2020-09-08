package com.bichi.daggerpractice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.bichi.daggerpractice.models.User;
import com.bichi.daggerpractice.ui.auth.AuthActivity;
import com.bichi.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObserver();
    }

    private void subscribeObserver(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null){
                    switch (userAuthResource.status){
                        case NOT_AUTHENTICATED:
                            login();
                            break;
                    }
                }
            }
        });
    }

    private void login() {
        Intent intent = new Intent(this,AuthActivity.class);
        startActivity(intent);
        finish();
    }
}

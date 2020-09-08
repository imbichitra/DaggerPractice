package com.bichi.daggerpractice.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bichi.daggerpractice.R;
import com.bichi.daggerpractice.models.User;
import com.bichi.daggerpractice.ui.main.MainActivity;
import com.bichi.daggerpractice.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    public static final String TAG = AuthActivity.class.getSimpleName();

    private EditText userId;
    private ProgressBar progressBar;

    private AuthViewModel viewModel;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    Drawable logo;
    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = new ViewModelProvider(this,viewModelProviderFactory).get(AuthViewModel.class);
        setLogo();

        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        subScribeObservers();
    }

    private void subScribeObservers(){
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null){
                    switch (userAuthResource.status){
                        case LOADING:
                            showProgressBar(true);
                            break;
                        case AUTHENTICATED:
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS "+userAuthResource.data.getEmail());
                            navigateToMainActivity();
                            break;
                        case ERROR:
                            showProgressBar(false);
                            Toast.makeText(AuthActivity.this, userAuthResource.message+"" +
                                    "\n enter id 1 to 10", Toast.LENGTH_SHORT).show();
                            break;
                        case NOT_AUTHENTICATED:
                            showProgressBar(false);
                            break;
                    }
                }
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar(boolean isVisible){
        if (isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
    private void attemptLogin() {
        if (TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }

    private void setLogo(){
        requestManager.load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }
}
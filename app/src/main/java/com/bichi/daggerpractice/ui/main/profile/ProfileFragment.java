package com.bichi.daggerpractice.ui.main.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bichi.daggerpractice.R;
import com.bichi.daggerpractice.models.User;
import com.bichi.daggerpractice.ui.auth.AuthResource;
import com.bichi.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class ProfileFragment extends DaggerFragment {

    private ProfileViewModel viewModel;
    private TextView email,username,website;
    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);
        
        viewModel = new ViewModelProvider(this,providerFactory).get(ProfileViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){


                        case AUTHENTICATED:{
                            setUserDetails(userAuthResource.data);
                            break;
                        }

                        case ERROR:{
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setUserDetails(User data) {
        email.setText(data.getEmail());
        username.setText(data.getUserName());
        website.setText(data.getWebsite());
    }

    private void setErrorDetails(String message) {
        email.setText(message);
        username.setText("error");
        website.setText("error");
    }
}
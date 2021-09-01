package com.sw.daggerpractice.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sw.daggerpractice.R;
import com.sw.daggerpractice.models.User;
import com.sw.daggerpractice.ui.auth.AuthResource;
import com.sw.daggerpractice.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import dagger.multibindings.IntoMap;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();


    private ProfileViewModel viewModel;
    private TextView userName, email, website;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), TAG, Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: ProfileFragment was Created !");
        userName = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        website = view.findViewById(R.id.website);
        viewModel = new ViewModelProvider(this, providerFactory).get(ProfileViewModel.class);
        subscribeObserver();
    }

    private void subscribeObserver() {
        viewModel.getAuthUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {

                        case AUTHENTICATED:
                            setUserDetils(userAuthResource.data);
                            break;

                        case ERROR:
                            setErrorDetils(userAuthResource.message);

                            break;

                    }
                }
            }
        });
    }

    private void setErrorDetils(String message) {
        userName.setText("Error");
        email.setText(message);
        website.setText("Error");
    }

    private void setUserDetils(User data) {
        userName.setText(data.getUsername());
        email.setText(data.getEmail());
        website.setText(data.getWebsite());
    }
}


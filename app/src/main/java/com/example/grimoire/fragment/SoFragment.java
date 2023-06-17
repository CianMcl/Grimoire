package com.example.grimoire.fragment;


import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.grimoire.Splash;
import com.example.grimoire.databinding.NavSoFragmentBinding;
import com.example.grimoire.viewmodel.SharedViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class SoFragment extends Fragment {

    public SoFragment(){}

    private NavSoFragmentBinding addBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseAuth.getInstance().signOut();
        @SuppressLint("RestrictedApi") Intent intent = new Intent(getApplicationContext(), Splash.class);
        startActivity(intent);

        return null;
    }



    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = NavSoFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        return view;
    }


     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
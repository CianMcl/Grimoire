package com.example.grimoire.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.grimoire.databinding.NavCharFragmentBinding;
import com.example.grimoire.databinding.NavCommFragmentBinding;
import com.example.grimoire.databinding.NavHomeFragmentBinding;
import com.example.grimoire.databinding.NavProfFragmentBinding;
import com.example.grimoire.viewmodel.SharedViewModel;

public class ProfFragment extends Fragment {
    private SharedViewModel model;
    private NavProfFragmentBinding addBinding;
    public ProfFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment
        addBinding = NavProfFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        addBinding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.resetButton.setVisibility(View.VISIBLE);
                addBinding.saveButton.setVisibility(View.VISIBLE);
                addBinding.cancelButton.setVisibility(View.VISIBLE);

                addBinding.updateButton.setVisibility(View.GONE);
            }
        });

        addBinding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.resetButton.setVisibility(View.GONE);
                addBinding.saveButton.setVisibility(View.GONE);
                addBinding.cancelButton.setVisibility(View.GONE);

                addBinding.updateButton.setVisibility(View.VISIBLE);
            }
        });














        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
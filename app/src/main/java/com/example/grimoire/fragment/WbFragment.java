package com.example.grimoire.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grimoire.R;
import com.example.grimoire.databinding.NavCharFragmentBinding;
import com.example.grimoire.databinding.NavCommFragmentBinding;
import com.example.grimoire.databinding.NavHomeFragmentBinding;
import com.example.grimoire.databinding.NavProfFragmentBinding;
import com.example.grimoire.databinding.NavWbFragmentBinding;
import com.example.grimoire.recycle.RandomNumListAdapter;
import com.example.grimoire.viewmodel.SharedViewModel;

public class WbFragment extends Fragment {
    private SharedViewModel model;
    private NavWbFragmentBinding addBinding;
    public WbFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //recycler view
        RecyclerView recyclerView;

        // Inflate the View for this fragment
        addBinding = NavWbFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter(1234));












        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
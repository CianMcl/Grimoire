package com.example.grimoire.fragment;

import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.fragment.app.Fragment;

        import com.example.grimoire.databinding.NavCharFragmentBinding;
        import com.example.grimoire.databinding.NavHomeFragmentBinding;
        import com.example.grimoire.viewmodel.SharedViewModel;

public class CharFragment extends Fragment {
    private SharedViewModel model;
    private NavCharFragmentBinding addBinding;
    public CharFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment
        addBinding = NavCharFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
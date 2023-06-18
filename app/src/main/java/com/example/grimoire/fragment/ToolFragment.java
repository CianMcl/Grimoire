package com.example.grimoire.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.grimoire.R;
import com.example.grimoire.databinding.NavCharFragmentBinding;
import com.example.grimoire.databinding.NavCommFragmentBinding;
import com.example.grimoire.databinding.NavHomeFragmentBinding;
import com.example.grimoire.databinding.NavProfFragmentBinding;
import com.example.grimoire.databinding.NavToolFragmentBinding;
import com.example.grimoire.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

import kotlin.random.URandomKt;

public class ToolFragment extends Fragment {
    private SharedViewModel model;
    private NavToolFragmentBinding addBinding;
    public ToolFragment(){}

    boolean open = false;
    boolean openDD = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = NavToolFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        List<Integer> diceType = new ArrayList<Integer>();
        diceType.add(4);
        diceType.add(6);
        diceType.add(8);
        diceType.add(10);
        diceType.add(12);
        diceType.add(20);
        diceType.add(100);

        final ArrayAdapter<Integer> typeAdapter = new ArrayAdapter<Integer>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, diceType);
        addBinding.txtInWhichDice.setAdapter(typeAdapter);

        List<Integer> diceNum = new ArrayList<Integer>();
        diceNum.add(1);
        diceNum.add(2);
        diceNum.add(3);
        diceNum.add(4);
        diceNum.add(5);
        diceNum.add(6);
        diceNum.add(7);
        diceNum.add(8);
        diceNum.add(9);
        diceNum.add(10);

        final ArrayAdapter<Integer> numAdapter = new ArrayAdapter<Integer>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, diceNum);
        addBinding.txtInHowDice.setAdapter(numAdapter);


        addBinding.btnDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //now visible

                if(open == false) {
                    addBinding.txtWhichDice.setVisibility(View.VISIBLE);
                    addBinding.txtInWhichDice.setVisibility(View.VISIBLE);

                    addBinding.txtHowDice.setVisibility(View.VISIBLE);
                    addBinding.txtInHowDice.setVisibility(View.VISIBLE);

                    addBinding.btnRoll.setVisibility(View.VISIBLE);


                    open = true;
                } else {
                    addBinding.txtWhichDice.setVisibility(View.GONE);
                    addBinding.txtInWhichDice.setVisibility(View.GONE);

                    addBinding.txtHowDice.setVisibility(View.GONE);
                    addBinding.txtInHowDice.setVisibility(View.GONE);

                    addBinding.btnRoll.setVisibility(View.GONE);

                    addBinding.txtResults.setVisibility(View.GONE);
                    addBinding.txtResultsOut.setVisibility(View.GONE);
                    open = false;
                }

            }

        });

        addBinding.btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //for copy paste
                //addBinding.txtInWhichDice
                //addBinding.txtInHowDice

                //this should work, come back to refactor
                //addBinding.txtInWhichDice.getItemAtPosition(addBinding.txtInWhichDice.getSelectedItemPosition());

                int typePos = addBinding.txtInWhichDice.getSelectedItemPosition();
                int type = (int) addBinding.txtInWhichDice.getItemAtPosition(typePos);
                int rollPos = addBinding.txtInHowDice.getSelectedItemPosition();
                int rolls = (int) addBinding.txtInHowDice.getItemAtPosition(rollPos);

                Log.e("Test of pos", typePos + " " + rollPos);
                Log.e("Test of number", type + " " + rolls);

                String results = "You rolled a D" + type + " " + rolls + " times.\nThe results are:";

                for (int i = 0; i < rolls; i++){
                    int res = (int) (Math.random() * (type-1)) + 1;
                    results = results + "\n" + res;
                }

                addBinding.txtResults.setVisibility(View.VISIBLE);
                addBinding.txtResultsOut.setText(results);
                addBinding.txtResultsOut.setVisibility(View.VISIBLE);


            }
        });

        addBinding.btnDummyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (openDD == false){
                    addBinding.btnDummyDataIns.setVisibility(View.VISIBLE);
                    openDD = true;
                } else {
                    addBinding.btnDummyDataIns.setVisibility(View.GONE);
                }
            }
        });

        addBinding.btnDummyDataIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "This is where the data is inserted", Toast.LENGTH_SHORT).show();
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
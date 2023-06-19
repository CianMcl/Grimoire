package com.example.grimoire.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.grimoire.R;
import com.example.grimoire.databinding.NavCharFragmentBinding;
import com.example.grimoire.databinding.NavCommFragmentBinding;
import com.example.grimoire.databinding.NavHomeFragmentBinding;
import com.example.grimoire.databinding.NavProfFragmentBinding;
import com.example.grimoire.databinding.NavToolFragmentBinding;
import com.example.grimoire.entity.User;
import com.example.grimoire.entity.World;
import com.example.grimoire.viewmodel.SharedViewModel;
import com.example.grimoire.viewmodel.UserViewModel;
import com.example.grimoire.viewmodel.WorldViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import kotlin.random.URandomKt;

public class ToolFragment extends Fragment {
    private SharedViewModel model;
    private NavToolFragmentBinding addBinding;
    private UserViewModel userViewModel;
    private WorldViewModel worldViewModel;
    FirebaseAuth mAuth;
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

        mAuth = FirebaseAuth.getInstance();
        //keep forgetting this bit lmao
        userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(UserViewModel.class); //ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);
        worldViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(WorldViewModel.class);

        addBinding.btnDummyDataIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity().getApplicationContext(), "This is where the data is inserted", Toast.LENGTH_SHORT).show();
                String testAcc1 = "test@email.com";
                String testAcc2 = "email@email.com";
                String password = "123456";
                Boolean check = false;

                try
                {
                    CompletableFuture<User> test = userViewModel.findByIDFuture(testAcc1);
                    if (test != null){
                        check = true;
                    }

                } catch (Exception e){
                    Log.e("Caught", "Should be null point");
                }

                //checks to see if a test account exists, don't think it's necessary to check both tbh
                //if (userViewModel.findByIDFuture(testAcc1) == null){
                if (check == true){
                    //add accs to db
                    User created = new User(testAcc1, "FakeFirst", "FakeLast", "FakeDob");
                    userViewModel.insert(created);
                    User created2 = new User(testAcc2, "FakeFirst2", "FakeLast2", "FakeDob2");
                    userViewModel.insert(created2);

                    //create firebase for later checks
                    mAuth.createUserWithEmailAndPassword(testAcc1, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            Toast.makeText(getActivity().getApplicationContext(), "Acc 1 Added", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                    mAuth.createUserWithEmailAndPassword(testAcc2, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Acc 2 Added", Toast.LENGTH_SHORT).show();
                                }
                            });

                    //set up world objects
                    World one = new World("test@email.com", "Elirea","A small forest island that is protected by the tree god", 4, "PUBLIC");
                    World two = new World("test@email.com", "Mondstadt","A city of wine, windmills and cats", 7, "PRIVATE");
                    World three = new World("test@email.com", "Wailes", "A land of mystery that covets a red dragon lord", 9, "PUBLIC");
                    World four = new World("test@email.com", "Lythos", "A floating city protected by a divine being", 2, "PRIVATE");
                    World five = new World("test@email.com", "Yokohema", "A city of the night which is protected by three rival groups", 10, "PUBLIC");
                    World six = new World("email@email.com", "Castamere", "An isolated island that is surrounded by walls and ruled by a cruel mage", 5, "PUBLIC");
                    World seven = new World("email@email.com", "Liyue", "The city of stone where the gods and humans have a somewhat complicated relationship", 3, "PRIVATE");
                    World eight = new World("email@email.com", "Darkest Peru", "Thick jungle covers this dark and humid land... but can you smell the marmalade?", 6, "PUBLIC");
                    World nine = new World("email@email.com", "Leblanc", "Get comfortable in this small town that is coveted for its magic coffee beans", 10, "PRIVATE");
                    World ten = new World("email@email.com", "Valentia", "A land that is split in two, and ruled by two dueling dragon gods", 1, "PUBLIC");

                    //add a bunch of worlds under each user
                    worldViewModel.insert(one);
                    worldViewModel.insert(two);
                    worldViewModel.insert(three);
                    worldViewModel.insert(four);
                    worldViewModel.insert(five);
                    worldViewModel.insert(six);
                    worldViewModel.insert(seven);
                    worldViewModel.insert(eight);
                    worldViewModel.insert(nine);
                    worldViewModel.insert(ten);

                    //remove buttons
                    addBinding.btnDummyData.setVisibility(View.GONE);
                    addBinding.btnDummyDataIns.setVisibility(View.GONE);

                    //toast
                    Toast.makeText(getActivity().getApplicationContext(), "Data has been inserted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Data already exists", Toast.LENGTH_LONG).show();
                }

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
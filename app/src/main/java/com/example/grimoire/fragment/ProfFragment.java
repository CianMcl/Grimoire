package com.example.grimoire.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grimoire.Register;
import com.example.grimoire.databinding.NavProfFragmentBinding;
import com.example.grimoire.entity.User;
import com.example.grimoire.viewmodel.SharedViewModel;
import com.example.grimoire.viewmodel.UserViewModel;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ProfFragment extends Fragment {
    private SharedViewModel model;
    TextView display_username, display_firstname, display_lastname;
    private UserViewModel userViewModel;
    FirebaseAuth auth;
    FirebaseUser user;
    private NavProfFragmentBinding addBinding;
    Toast toast;

    String unTest, unFirst, unLast;

    public ProfFragment() {}

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = NavProfFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(UserViewModel.class); //ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        String currentEmail = user.getEmail();
        Log.e("Test", "Current email is " + currentEmail);

        //tests for display
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            //if(userViewModel.findByIDFuture(currentEmail) == null){
            //Log.e("","Equals null");
            try {
                CompletableFuture<User> profileTester = userViewModel.findByIDFuture(currentEmail);
                //String unTest = "null";
                try {
                    unTest = profileTester.get().userName;
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //String unFirst = "null";
                try {
                    unFirst = profileTester.get().firstName;
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //String unLast = "null";
                try {
                    unLast = profileTester.get().lastName;
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                addBinding.displayUsername.setText(unTest);
                addBinding.displayFirstname.setText(unFirst);
                addBinding.displayLastname.setText(unLast);
            } catch (Exception e) {
                Log.e("Error", "future fuckup");
                addBinding.displayUsername.setText("Error");
                addBinding.displayFirstname.setText("Error");
                addBinding.displayLastname.setText("Error");
            }
        }

        //buttons + visibility updates
        addBinding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //now visible
                //addBinding.usernameTextField.setVisibility(View.VISIBLE);
                addBinding.firstnameTextField.setVisibility(View.VISIBLE);
                addBinding.surnameTextField.setVisibility(View.VISIBLE);

                addBinding.dobber.setVisibility(View.VISIBLE);
                addBinding.dobbox.setVisibility(View.VISIBLE);

                addBinding.editmode.setVisibility(View.VISIBLE);

                //addBinding.resetButton.setVisibility(View.VISIBLE);
                addBinding.saveButton.setVisibility(View.VISIBLE);
                addBinding.cancelButton.setVisibility(View.VISIBLE);

                //now hidden
                addBinding.updateButton.setVisibility(View.GONE);

                addBinding.menuUsername.setVisibility(View.GONE);
                addBinding.menuFirstname.setVisibility(View.GONE);
                addBinding.menuLastname.setVisibility(View.GONE);

                addBinding.displayUsername.setVisibility(View.GONE);
                addBinding.displayLastname.setVisibility(View.GONE);
                addBinding.displayFirstname.setVisibility(View.GONE);
            }
        });

        addBinding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //now hidden
                //addBinding.usernameTextField.setVisibility(View.GONE);
                addBinding.firstnameTextField.setVisibility(View.GONE);
                addBinding.surnameTextField.setVisibility(View.GONE);

                addBinding.editmode.setVisibility(View.GONE);

                addBinding.dobber.setVisibility(View.GONE);
                addBinding.dobbox.setVisibility(View.GONE);

                //addBinding.resetButton.setVisibility(View.GONE);
                addBinding.saveButton.setVisibility(View.GONE);
                addBinding.cancelButton.setVisibility(View.GONE);

                //now visible
                addBinding.updateButton.setVisibility(View.VISIBLE);

                addBinding.menuUsername.setVisibility(View.VISIBLE);
                addBinding.menuFirstname.setVisibility(View.VISIBLE);
                addBinding.menuLastname.setVisibility(View.VISIBLE);

                addBinding.displayUsername.setVisibility(View.VISIBLE);
                addBinding.displayLastname.setVisibility(View.VISIBLE);
                addBinding.displayFirstname.setVisibility(View.VISIBLE);

                addBinding.refreshButton.setVisibility(View.VISIBLE);
            }
        });

        addBinding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String id = currentEmail;
                Boolean cont1 = false;
                Boolean cont2 = false;
                Log.e("Current first ", unFirst);
                Log.e("Current last ", unLast);

                //not empty validation
                String first = addBinding.firstnameTextField.getEditText().getText().toString();
                Log.e("Potential first", first);
                if (first.equals("")){
                    Log.e("first equals space", "");
                    Toast.makeText(getActivity().getApplicationContext(), "Please fill out your first name", Toast.LENGTH_SHORT).show();
                    first = unFirst;
                    Log.e("Manually set to previous ", first);
                } else {
                    cont1 = true;
                    Log.e("String first ", first);
                }

                String last = addBinding.surnameTextField.getEditText().getText().toString();
                Log.e("Potential last", last);
                if (last.equals("")){
                    Log.e("Last equals space", "");
                    Toast.makeText(getActivity().getApplicationContext(), "Please fill out your last name", Toast.LENGTH_SHORT).show();
                    last = unLast;
                    Log.e("Log Last: ", last);
                } else {
                    cont2 = true;
                    Log.e("String last ", last);
                }

                if (cont1 && cont2){
                                //actually write to db
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                    CompletableFuture<User> profileTester = userViewModel.findByIDFuture(currentEmail);
                                    String finalFirst = first;
                                    String finalLast = last;
                                    profileTester.thenApply(user -> {
                                        if (user != null) {
                                            user.firstName = finalFirst;
                                            user.lastName = finalLast;
                                            userViewModel.update(user);
                                            Log.e("Change", "Changed");

                                            Toast.makeText(getActivity().getApplicationContext(), "Changes saved, you can now close edit mode.", Toast.LENGTH_LONG).show();


                                            //change display
                                            //addBinding.displayFirstname.setText(first);
                                            //addBinding.displayLastname.setText(last);

                                            //this bit strikes me as a bad idea, but I want to see if it works
                                            //return view;
                                            //yeah didn't work
                                            //addBinding.cancelButton.setText("Close");



                                        }
                                    return user;
                                    });
                                }

                            }}
        });

        addBinding.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("Refresh", "add code, but we good");
                //addBinding = null;
                //addBinding

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    //if(userViewModel.findByIDFuture(currentEmail) == null){
                    //Log.e("","Equals null");
                    try {
                        CompletableFuture<User> profileTester = userViewModel.findByIDFuture(currentEmail);
                        String unTest = "null";
                        try {
                            unTest = profileTester.get().userName;
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        String unFirst = "null";
                        try {
                            unFirst = profileTester.get().firstName;
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        String unLast = "null";
                        try {
                            unLast = profileTester.get().lastName;
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        addBinding.displayUsername.setText(unTest);
                        addBinding.displayFirstname.setText(unFirst);
                        addBinding.displayLastname.setText(unLast);
                    } catch (Exception e) {
                        Log.e("Error", "future fuckup");
                        addBinding.displayUsername.setText("Error");
                        addBinding.displayFirstname.setText("Error");
                        addBinding.displayLastname.setText("Error");
                    }
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

 /* dead code zone

            CompletableFuture<User> currentUser = userViewModel.findByIDFuture(currentEmail);

            try {
                String fname = currentUser.get().firstName;
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {


            //using allusers as test, will update for single
            @Override
            public void onChanged(@Nullable final List<User> users) {
                String allUsers = "";
                for (User temp : users){
                    String userDetails = (temp.userName + " " + temp.firstName + " " + temp.lastName);

                    allUsers = allUsers + System.getProperty("line.separator") + userDetails;

                    }
                }
            }
        );
*/


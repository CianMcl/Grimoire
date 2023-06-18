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
import androidx.lifecycle.Observer;

import com.example.grimoire.databinding.NavProfFragmentBinding;
import com.example.grimoire.entity.User;
import com.example.grimoire.viewmodel.SharedViewModel;
import com.example.grimoire.viewmodel.UserViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ProfFragment extends Fragment {
    private SharedViewModel model;
    TextView display_username, display_firstname, display_lastname;
    private UserViewModel userModel;
    FirebaseAuth auth;
    //Button logout;
    //TextView details;
    FirebaseUser user;
    private NavProfFragmentBinding addBinding;
    private UserViewModel userViewModel;
    public ProfFragment(){}

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = NavProfFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        
        //set text based on user info
        //addBinding.displayUsername.setText("Placeholder");
        //addBinding.displayLastname.setText("Placeholder");
        //addBinding.displayFirstname.setText("Placeholder");

        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();

        String currentEmail = user.getEmail();
        Log.e("Test", currentEmail);

        //tests for display
        /*
        User profileTester = userViewModel.getUserVM(currentEmail);

        String unTest = profileTester.userName;
        String unFirst = profileTester.firstName;
        String unLast = profileTester.lastName;

        addBinding.displayUsername.setText(unTest);
        addBinding.displayFirstname.setText(unFirst);
        addBinding.displayLastname.setText(unLast);
        */


        /*
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
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


        //buttons + visibility updates
        addBinding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //now visible
                addBinding.usernameTextField.setVisibility(View.VISIBLE);
                addBinding.firstnameTextField.setVisibility(View.VISIBLE);
                addBinding.surnameTextField.setVisibility(View.VISIBLE);

                addBinding.dobber.setVisibility(View.VISIBLE);
                addBinding.dobbox.setVisibility(View.VISIBLE);

                addBinding.resetButton.setVisibility(View.VISIBLE);
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
                addBinding.usernameTextField.setVisibility(View.GONE);
                addBinding.firstnameTextField.setVisibility(View.GONE);
                addBinding.surnameTextField.setVisibility(View.GONE);

                addBinding.dobber.setVisibility(View.GONE);
                addBinding.dobbox.setVisibility(View.GONE);

                addBinding.resetButton.setVisibility(View.GONE);
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
            }
        });

        //db test stuff
        //userModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication().create(UserViewModel.class));

        //userModel.get













        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
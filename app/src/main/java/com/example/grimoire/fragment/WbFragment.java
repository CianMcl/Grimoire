package com.example.grimoire.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grimoire.entity.World;
import com.example.grimoire.recycle.RecyclerViewHolder;
import com.example.grimoire.recycle.WorldListUserAdapter;
import com.example.grimoire.viewmodel.SharedViewModel;
import com.example.grimoire.viewmodel.UserViewModel;
import com.example.grimoire.viewmodel.WorldViewModel;

import com.google.firebase.auth.FirebaseAuth;

import com.example.grimoire.R;
import com.example.grimoire.databinding.NavWbFragmentBinding;
import com.example.grimoire.recycle.RandomNumListAdapter;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WbFragment extends Fragment {
    private SharedViewModel model;

    private UserViewModel userViewModel;
    private WorldViewModel worldViewModel;
    FirebaseAuth mAuth;

    String curName;
    String curDesc;
    String curPub;

    LiveData<List<World>> worldList;

    //private RecyclerView.Adapter wAdapter;

    private NavWbFragmentBinding addBinding;
    public WbFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        mAuth = FirebaseAuth.getInstance();
        //keep forgetting this bit lmao
        userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(UserViewModel.class); //ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);
        worldViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(WorldViewModel.class);

        //recycler view
        RecyclerView recyclerView;
        //as insisted this be an array. I'm not sure why tbh
        final boolean[] recycleOpen = {true};
        final boolean[] recycleOpenEdit = {true};

        String currentUser = mAuth.getCurrentUser().getEmail();
        //worldList = worldViewModel.getAllUserWorld(currentUser);
        //abandoning recycler view because it isn't playing nice with the livedata list

        //okay look, it's fucking stupid but I'm going to try the recycle again

        //the below actually works, and it took three bloody minutes. I'm going to try stealing the list.


        worldViewModel.getAllUserWorld(currentUser).observe(getViewLifecycleOwner(), new Observer<List<World>>() {
            @Override
            public void onChanged(final List<World> worlds) {
                String userWorlds ="";
                for (World temp: worlds){
                    String interiorWorlds = (
                            "ID: " + temp.worldId +"\n" +
                            "Name: " + temp.name + "\n" +
                            "Description: " + temp.description
                            );
                    userWorlds = userWorlds + System.getProperty("line.separator") + interiorWorlds;
                }

                addBinding.txtAllUserWorld.setText(userWorlds);
            }

        });

        //god damn it
        //it's like... a sliver away from being done
        //but it's just out of reach. I've got less than two hours left and I've already spent over three on it


        // Inflate the View for this fragment
        addBinding = NavWbFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //default from guide source in function
        //recyclerView.setAdapter(new RandomNumListAdapter(1234));

        //my adapter
        //recyclerView.setAdapter(new WorldListUserAdapter(worldList));
        recyclerView.setAdapter(new WorldListUserAdapter());
        //recyclerView.setVisibility(View.VISIBLE);



        addBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recycleOpen[0] == true)
                {

                    //hide splash
                    addBinding.txtYours.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    addBinding.txtAllUserWorld.setVisibility(View.GONE);
                    addBinding.btnAdd.setVisibility(View.GONE);
                    addBinding.btnEdit.setVisibility(View.GONE);

                    //show add buttons
                    addBinding.btnSave.setVisibility(View.VISIBLE);
                    addBinding.btnCancel.setVisibility(View.VISIBLE);

                    //forms ew
                    //World one = new World("test@email.com", "name","description", 0, "PRIVATE");
                    //worldViewModel.insert(one);
                    addBinding.txtAdd.setVisibility(View.VISIBLE);

                    addBinding.txtFWorldName.setVisibility(View.VISIBLE);
                    addBinding.txtFWorldDescription.setVisibility(View.VISIBLE);
                    addBinding.txtFPublic.setVisibility(View.VISIBLE);

                    recycleOpen[0] = false;
                    }

                else
                {
                    recyclerView.setVisibility(View.GONE);
                    //recyclerView.setVisibility(View.VISIBLE);
                    addBinding.btnAdd.setVisibility(View.VISIBLE);
                    addBinding.btnEdit.setVisibility(View.VISIBLE);
                    addBinding.txtAllUserWorld.setVisibility(View.VISIBLE);
                    addBinding.txtYours.setVisibility(View.VISIBLE);

                    //all off
                    addBinding.btnSave.setVisibility(View.GONE);
                    addBinding.btnSaveEdit.setVisibility(View.GONE);
                    addBinding.btnCancel.setVisibility(View.GONE);
                    addBinding.txtAdd.setVisibility(View.GONE);

                    addBinding.txtAdd.setVisibility(View.GONE);
                    addBinding.txtFWorldName.setVisibility(View.GONE);
                    addBinding.txtFWorldDescription.setVisibility(View.GONE);
                    addBinding.txtFPublic.setVisibility(View.GONE);

                    recycleOpen[0] = true;
                }


            }
        });

        addBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recycleOpenEdit[0] == true)
                {
                    recycleOpenEdit[0] = false;
                    //hide splash
                    addBinding.txtYours.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    addBinding.btnAdd.setVisibility(View.GONE);
                    addBinding.btnEdit.setVisibility(View.GONE);
                    addBinding.txtAllUserWorld.setVisibility(View.GONE);

                    //show edit buttons
                    addBinding.btnSaveEdit.setVisibility(View.VISIBLE);
                    addBinding.btnCancel.setVisibility(View.VISIBLE);

                    //show form structure
                    addBinding.txtEdit.setVisibility(View.VISIBLE);
                    addBinding.txtFWorldIdEdit.setVisibility(View.VISIBLE);
                    addBinding.txtFWorldNameEdit.setVisibility(View.VISIBLE);
                    addBinding.txtFWorldDescriptionEdit.setVisibility(View.VISIBLE);
                    addBinding.txtFPublicEdit.setVisibility(View.VISIBLE);

                }

                else
                {
                    recyclerView.setVisibility(View.GONE);
                    //recyclerView.setVisibility(View.VISIBLE);
                    addBinding.btnAdd.setVisibility(View.VISIBLE);
                    addBinding.btnEdit.setVisibility(View.VISIBLE);
                    addBinding.txtAllUserWorld.setVisibility(View.VISIBLE);
                    addBinding.txtYours.setVisibility(View.VISIBLE);

                    //all off
                    addBinding.btnSave.setVisibility(View.GONE);
                    addBinding.btnSaveEdit.setVisibility(View.GONE);
                    addBinding.btnCancel.setVisibility(View.GONE);

                    addBinding.txtEdit.setVisibility(View.GONE);
                    addBinding.txtFWorldIdEdit.setVisibility(View.GONE);
                    addBinding.txtFWorldNameEdit.setVisibility(View.GONE);
                    addBinding.txtFWorldDescriptionEdit.setVisibility(View.GONE);
                    addBinding.txtFPublicEdit.setVisibility(View.GONE);

                    recycleOpenEdit[0] = true;
                }


            }
        });

        addBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (){}
                //just turn everything off and then turn on the base stuff tbh

                //on
                recyclerView.setVisibility(View.GONE);
                //recyclerView.setVisibility(View.VISIBLE);
                addBinding.btnAdd.setVisibility(View.VISIBLE);
                addBinding.btnEdit.setVisibility(View.VISIBLE);
                addBinding.txtAllUserWorld.setVisibility(View.VISIBLE);
                addBinding.txtYours.setVisibility(View.VISIBLE);

                //all off
                addBinding.btnSave.setVisibility(View.GONE);
                addBinding.btnSaveEdit.setVisibility(View.GONE);
                addBinding.btnCancel.setVisibility(View.GONE);

                addBinding.txtAdd.setVisibility(View.GONE);
                addBinding.txtFWorldName.setVisibility(View.GONE);
                addBinding.txtFWorldDescription.setVisibility(View.GONE);
                addBinding.txtFPublic.setVisibility(View.GONE);

                addBinding.txtEdit.setVisibility(View.GONE);
                addBinding.txtFWorldIdEdit.setVisibility(View.GONE);
                addBinding.txtFWorldNameEdit.setVisibility(View.GONE);
                addBinding.txtFWorldDescriptionEdit.setVisibility(View.GONE);
                addBinding.txtFPublicEdit.setVisibility(View.GONE);

                addBinding.txtFWorldIdEdit.getEditText().setText("");
                addBinding.txtFWorldNameEdit.getEditText().setText("");
                addBinding.txtFWorldDescription.getEditText().setText("");
                addBinding.txtFPublicEdit.getEditText().setText("");
                addBinding.txtFWorldName.getEditText().setText("");
                addBinding.txtFWorldDescription.getEditText().setText("");
                addBinding.txtFPublic.getEditText().setText("");

                //this is where the issue was, I think
                //didn't need the extra one, but it's here for now
                recycleOpen[0] = true;
                recycleOpenEdit[0] = true;
            }
        });

        addBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean workingSave = false;
                //get info
                String newName = addBinding.txtFWorldName.getEditText().getText().toString();
                String newDesc = addBinding.txtFWorldDescription.getEditText().getText().toString();
                String newPub = addBinding.txtFPublic.getEditText().getText().toString();
                String newEmail = mAuth.getCurrentUser().getEmail();

                //validations
                boolean validInput = true;

                if ((newName == null) || (newDesc == null) || (newPub == null))
                {
                    validInput = false;
                    Toast.makeText(getActivity().getApplicationContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }


                //save into db
                if (validInput == true) {
                    World newWorld = new World(newEmail, newName, newDesc, 0, newPub);
                    try {
                        worldViewModel.insert(newWorld);
                        workingSave = true;
                        Toast.makeText(getActivity().getApplicationContext(), "World Saved!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.e("Error", "Writing to DB");
                    }
                }


                //reset to home
                if (workingSave == true) {
                    recyclerView.setVisibility(View.GONE);
                    //recyclerView.setVisibility(View.VISIBLE);
                    addBinding.txtAllUserWorld.setVisibility(View.VISIBLE);
                    addBinding.btnAdd.setVisibility(View.VISIBLE);
                    addBinding.btnEdit.setVisibility(View.VISIBLE);
                    addBinding.txtYours.setVisibility(View.VISIBLE);

                    //all off
                    addBinding.btnSave.setVisibility(View.GONE);
                    addBinding.btnSaveEdit.setVisibility(View.GONE);
                    addBinding.btnCancel.setVisibility(View.GONE);
                    addBinding.txtAdd.setVisibility(View.GONE);

                    addBinding.txtAdd.setVisibility(View.GONE);
                    addBinding.txtFWorldName.setVisibility(View.GONE);
                    addBinding.txtFWorldDescription.setVisibility(View.GONE);
                    addBinding.txtFPublic.setVisibility(View.GONE);

                    recycleOpen[0] = true;
                }
            }
        });

        addBinding.btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean[] workingEditSave = {false};
                final boolean[] hasAccess = {false};

                //id checks
                String editId = addBinding.txtFWorldIdEdit.getEditText().getText().toString();
                int idCheck = Integer.parseInt(editId);

                //check user has access
                try{
                    CompletableFuture<World> editWorld = worldViewModel.findByIDFuture(idCheck);

                    String worldUser = editWorld.get().WUserId;
                    String currentUser = mAuth.getCurrentUser().getEmail();

                    if (worldUser.equals(currentUser)){
                        hasAccess[0] = true;
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "That world doesn't belong to you. Enter another ID", Toast.LENGTH_SHORT).show();}

                } catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(), "That world doesn't exist. Enter another ID.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", "Checking access");
                }


                //get current info
                if(hasAccess[0] == true) {
                    try {
                        CompletableFuture<World> editWorld = worldViewModel.findByIDFuture(idCheck);

                        curName = "Error";
                        curDesc = "Error";
                        curPub = "Error";

                        try {
                            curName = editWorld.get().name;
                            Log.e("curname", curName);
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            curDesc = editWorld.get().description;
                            Log.e("curdes", curDesc);
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            curPub = editWorld.get().privacy;
                            Log.e("curpriv", curPub);
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }


                        //get edit info
                        String editName = addBinding.txtFWorldNameEdit.getEditText().getText().toString().trim();
                        String editDesc = addBinding.txtFWorldDescriptionEdit.getEditText().getText().toString().trim();
                        String editPub = addBinding.txtFPublicEdit.getEditText().getText().toString().trim();

                        //validations
                        //keep what's there if nothing is changed
                        if (editName.isEmpty()) {
                            Log.e("Empty1", editName + " + " + curName);
                            editName = curName;
                            Log.e("Empty2", editName + " + " + curName);
                        }
                        if (editDesc.isEmpty()) {
                            Log.e("Empty3", editDesc + " + " + curDesc);
                            editDesc = curDesc;
                            Log.e("Empty4", editDesc + " + " + curDesc);
                        }
                        if (editPub.isEmpty()) {
                            Log.e("Empty5", editPub + " + " + curPub);
                            editPub = curPub;
                            Log.e("Empty6", editPub + " + " + curPub);
                        }

                        //other validations


                        //save into db
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            CompletableFuture<World> worldCompletableFuture =
                                    worldViewModel.findByIDFuture(idCheck);

                            //not sure why AS wants these as final, but go off
                            String finalEditName = editName;
                            String finalEditDesc = editDesc;
                            String finalEditPub = editPub;
                            worldCompletableFuture.thenApply(world -> {
                                if (world != null) {
                                    world.name = finalEditName;
                                    world.description = finalEditDesc;
                                    world.privacy = finalEditPub;
                                    worldViewModel.update(world);

                                }
                                return world;
                            });
                        }
                        Toast.makeText(getActivity().getApplicationContext(), "World Updated!", Toast.LENGTH_SHORT).show();
                        workingEditSave[0] = true;
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Please enter a valid world ID", Toast.LENGTH_SHORT).show();
                    }
                }

                //reset fragment to home
                if (workingEditSave[0] == true) {
                    //recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    addBinding.btnAdd.setVisibility(View.VISIBLE);
                    addBinding.btnEdit.setVisibility(View.VISIBLE);
                    addBinding.txtYours.setVisibility(View.VISIBLE);
                    addBinding.txtAllUserWorld.setVisibility(View.VISIBLE);

                    //all off
                    addBinding.btnSave.setVisibility(View.GONE);
                    addBinding.btnSaveEdit.setVisibility(View.GONE);
                    addBinding.btnCancel.setVisibility(View.GONE);
                    addBinding.txtAdd.setVisibility(View.GONE);

                    addBinding.txtEdit.setVisibility(View.GONE);
                    addBinding.txtFWorldIdEdit.setVisibility(View.GONE);
                    addBinding.txtFWorldNameEdit.setVisibility(View.GONE);
                    addBinding.txtFWorldDescriptionEdit.setVisibility(View.GONE);
                    addBinding.txtFPublicEdit.setVisibility(View.GONE);

                    recycleOpen[0] = true;

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
package com.example.grimoire.recycle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grimoire.R;

import com.example.grimoire.entity.World;
import com.example.grimoire.fragment.WbFragment;
import com.example.grimoire.viewmodel.WorldViewModel;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

//some code from https://www.digitalocean.com/community/tutorials/android-livedata
public class WorldListUserAdapter extends RecyclerView.Adapter<NewRecyclerViewHolder> {

    private WorldViewModel worldViewModel;
    LiveData<List<World>> worldList;
    ArrayList<World> testWorld;
    FirebaseAuth mAuth;
    int count;

    //public WorldListUserAdapter(LiveData<List<World>> given){this.worldList = given;}

    @Override
    public int getItemViewType(final int position) {
        //return R.layout.frame_textview;
        return R.layout.list_item_row;
    }

    @NonNull
    @Override
    public NewRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row, parent, false);
        return new NewRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewRecyclerViewHolder holder, int position) {
        mAuth = FirebaseAuth.getInstance();
        String currentUser = mAuth.getCurrentUser().getEmail();

        /*
        worldViewModel.getAllUserWorld(currentUser).observe(this, new Observer<List<World>>() {
            @Override
            public void onChanged(final List<World> worlds) {
                String userWorlds = "";
                for (World temp : worlds) {
                    holder.getWorldId().setText(temp.worldId);
                    holder.getWorldName().setText(temp.name);
                    holder.getWorldDescription().setText(temp.description);
                }
            }
        });
        */

    }

    @Override
    public int getItemCount() {
        //return testWorld.size();
        return 100;
    }



}


    /*
    @Override
    public void onBindViewHolder(@NonNull NewRecyclerViewHolder holder, int position) {

        try{
            final Observer<Integer> idObserver = new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    holder.getWorldId().setText(integer);
                }
            };

            final Observer<String> nameObserver = new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    holder.getWorldName().setText(s);
                }
            };

            final Observer<String> descObserver = new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    holder.getWorldDescription().setText(s);
                }
            };



        } catch (Exception e) {
            Log.e("Error",  "using worldList'");
        }

        /*
        try {
            World cur = worldList.getValue().get(position);

            int curId = cur.worldId;
            String curName = cur.name;
            String curDesc = cur.description;

            holder.getWorldId().setText(curId);
            holder.getWorldName().setText(curName);
            holder.getWorldDescription().setText(curDesc);
        } catch(Exception e) {
            Log.e("Error in", "livelist pt 2");
        }
        */



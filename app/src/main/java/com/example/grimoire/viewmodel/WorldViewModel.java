package com.example.grimoire.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.grimoire.entity.World;
import com.example.grimoire.repository.GrimoireRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WorldViewModel extends AndroidViewModel {
    private GrimoireRepository gRepo;
    private LiveData<List<World>> allWorld;
    private LiveData<List<World>> allUserWorld;

    public WorldViewModel(@NonNull Application application) {
        super(application);
        gRepo = new GrimoireRepository(application);
        allWorld = gRepo.getAllWorlds();
    }

    public LiveData<List<World>> getAllWorlds() {
        return allWorld;
    }

    public CompletableFuture<World> findByIDFuture(final int worldId){
        return gRepo.findByIDFuture(worldId);
    }

    public void insert(World world){
        gRepo.insert(world);
    }

    public void deleteAll(){
        gRepo.deleteAll();
    }

    public void update(World world){
        gRepo.updateWorld(world);
    }

}

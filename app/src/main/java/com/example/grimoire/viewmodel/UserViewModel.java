package com.example.grimoire.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.grimoire.entity.User;
import com.example.grimoire.repository.GrimoireRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserViewModel extends AndroidViewModel {

    private GrimoireRepository gRepo;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        gRepo = new GrimoireRepository(application);
        allUsers = gRepo.getAllUsers();

    }

    /*
    public CompletableFuture<User> findByIDFuture(final int userId){
        return gRepo.findByIDFuture(userId);
    }

     */

    public CompletableFuture<User> findByIDFuture(final String userName){
        return gRepo.findByIDFuture(userName);
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public void insert(User user){
        gRepo.insert(user);
    }

    public void deleteAll(){
        gRepo.deleteAll();
    }

    public void update(User user){
        gRepo.updateUser(user);
    }

    public LiveData<User> getSingleUser(final int userId){

        //User singUser = gRepo.findByIDFuture();
        return null;
    }

    public User getUserVM(final String userId){
        Log.e("Test 2", "Starting VM");
        User vmReturn = gRepo.getUser(userId);
        return vmReturn;
    }

}

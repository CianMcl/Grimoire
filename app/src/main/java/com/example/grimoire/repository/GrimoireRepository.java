package com.example.grimoire.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.grimoire.dao.UserDAO;
import com.example.grimoire.database.GrimoireDatabase;
import com.example.grimoire.entity.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class GrimoireRepository {

    private UserDAO userDao;
    private LiveData<List<User>> allUsers;

    public GrimoireRepository(Application application){
        GrimoireDatabase db = GrimoireDatabase.getInstance(application);
        userDao = db.userDao();
        allUsers = userDao.getAll();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public User getUser(String userId){
        Log.e("Test 3", "Starting getUser");
        User toReturn = new User();
        toReturn = userDao.findByID(userId);
        return toReturn;
    }

    public void insert(final User user){
        GrimoireDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
            }
        });
    }

    public void deleteAll(){
        GrimoireDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.deleteAll();
            }
        });
    }

    public void delete(final User user){
        GrimoireDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.delete(user);

            }
        });
    }

    public void updateUser(final User user){
        GrimoireDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updateUser(user);
            }
        });
    }

    /*
    public CompletableFuture<User> findByIDFuture(final int userId){
        return CompletableFuture.supplyAsync(new Supplier<User>() {
            @Override
            public User get() {
                return userDao.findByID(userId);
            }
        }, GrimoireDatabase.databaseWriteExecutor);
    }
*/

    public CompletableFuture<User> findByIDFuture(String userName) {
        return CompletableFuture.supplyAsync(new Supplier<User>() {
            @Override
            public User get() {
                return userDao.findByID(userName);
            }
        }, GrimoireDatabase.databaseWriteExecutor);
    }
    }


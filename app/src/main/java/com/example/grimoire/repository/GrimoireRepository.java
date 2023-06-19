package com.example.grimoire.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.grimoire.dao.UserDAO;
import com.example.grimoire.dao.WorldDAO;
import com.example.grimoire.database.GrimoireDatabase;
import com.example.grimoire.entity.User;
import com.example.grimoire.entity.World;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class GrimoireRepository {

    private UserDAO userDao;
    private WorldDAO worldDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<World>> allWorlds;
    private LiveData<List<World>> allUserWorld;

    public GrimoireRepository(Application application){
        GrimoireDatabase db = GrimoireDatabase.getInstance(application);
        userDao = db.userDao();
        worldDao = db.worldDao();
        allUsers = userDao.getAll();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
    public LiveData<List<World>> getAllWorlds() {return allWorlds;}

    public LiveData<List<World>> getAllUserWorld(String userId){
        allUserWorld = worldDao.getAllUserWorld(userId);
        return allUserWorld;
    }

    /*
    public CompletableFuture<LiveData<List<World>>> getAllUserWorlds(String userId)
    {
        return CompletableFuture.supplyAsync(new Supplier<LiveData<List<World>>>() {
            @Override
            //public World get() {return worldDao.findById(worldId);}
            public LiveData<List<World>> get(){
                return worldDao.getAllUserWorld(userId);
            }
        });
    }

     */

    public User getUser(String userId){
        Log.e("Test 3", "Starting getUser");
        User toReturn = new User();
        Log.e("created fake", "calling dao");
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
    public void insert(final World world){
        GrimoireDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                worldDao.insert(world);
            }
        });
    }

    public void deleteAll(){
        GrimoireDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.deleteAll();
                worldDao.deleteAll();
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

    public void delete(final World world){
        GrimoireDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                worldDao.delete(world);
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

    public void updateWorld(final World world){
        GrimoireDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                worldDao.updateWorld(world);
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

    public CompletableFuture<World> findByIDFuture(int worldId)
    {
        return CompletableFuture.supplyAsync(new Supplier<World>() {
            @Override
            public World get() {
                return worldDao.findById(worldId);
            }
        });
    }



    public CompletableFuture<User> findByIDFuture(String userName) {
        return CompletableFuture.supplyAsync(new Supplier<User>() {
            @Override
            public User get() {
                return userDao.findByID(userName);
            }
        }, GrimoireDatabase.databaseWriteExecutor);
    }





}
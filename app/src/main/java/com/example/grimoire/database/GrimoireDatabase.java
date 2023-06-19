package com.example.grimoire.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.grimoire.entity.User;
import com.example.grimoire.entity.World;

import com.example.grimoire.dao.UserDAO;
import com.example.grimoire.dao.WorldDAO;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//per lab 10

@Database(  entities = {User.class, World.class},
            version = 3,
            exportSchema = false)
public abstract class GrimoireDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
    public abstract WorldDAO worldDao();
    private static GrimoireDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized GrimoireDatabase getInstance(final Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    GrimoireDatabase.class, "GrimoireDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}

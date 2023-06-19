package com.example.grimoire.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.grimoire.entity.World;

import java.util.List;

@Dao
public interface WorldDAO {

    @Query("SELECT * FROM world ORDER BY name ASC")
    LiveData<List<World>> getAll();

    @Query("SELECT * FROM world WHERE WUserId = :userId")
    LiveData<List<World>> getAllUserWorld(String userId);

    @Query("SELECT * FROM world WHERE worldId = :worldId LIMIT 1")
    World findById(int worldId);

    @Insert
    void insert(World world);

    @Delete
    void delete(World world);

    @Update
    void updateWorld(World world);

    @Query("DELETE FROM world")
    void deleteAll();

}

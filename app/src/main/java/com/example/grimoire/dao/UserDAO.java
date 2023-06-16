package com.example.grimoire.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.grimoire.entity.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM user ORDER BY userId ASC")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE userId = :searchID LIMIT 1")
    User findByID(int searchID);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void updateUser(User user);

    @Query("DELETE FROM user")
    void deleteAll();

}

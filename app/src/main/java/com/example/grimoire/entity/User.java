package com.example.grimoire.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    //@PrimaryKey (autoGenerate = true)
    //public int userId;

    @PrimaryKey
    @ColumnInfo (name = "user_name")
    @NonNull
    public String userName;

    @ColumnInfo (name = "first_name")
    @Nullable
    public String firstName;

    @ColumnInfo (name = "last_name")
    @Nullable
    public String lastName;

    @ColumnInfo (name = "date_of_birth")
    @Nullable
    public String dob;

    public User(){};

    public User (@NonNull String userName, String firstName, String lastName, String dob)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
}

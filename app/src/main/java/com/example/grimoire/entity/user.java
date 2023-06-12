package com.example.grimoire.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class user {

    @PrimaryKey (autoGenerate = true)
    public int userId;

    @ColumnInfo (name = "user_name")
    @NonNull
    public String userName;

    @ColumnInfo (name = "email")
    @NonNull
    public String email;

    @ColumnInfo (name = "password")
    @NonNull
    public String password;

    @ColumnInfo (name = "date_of_birth")
    @NonNull
    public String dob;

    public user (@NonNull String userName, @NonNull String email, @NonNull String dob, @NonNull String password){
        this.userName = userName;
        this.email = email;
        this.dob = dob;
        this.password = password;
    }
}

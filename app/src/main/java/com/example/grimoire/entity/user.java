package com.example.grimoire.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @ColumnInfo (name = "first_name")
    @Nullable
    public String email;

    @ColumnInfo (name = "last_name")
    @Nullable
    public String password;

    @ColumnInfo (name = "date_of_birth")
    @Nullable
    public String dob;

    public user (@NonNull String userName, @NonNull String email, @NonNull String dob, @NonNull String password){
        this.userName = userName;
        this.email = email;
        this.dob = dob;
        this.password = password;
    }
}

package com.example.grimoire.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (foreignKeys = {
        @ForeignKey(
        entity = User.class,
        parentColumns = "user_name",
        childColumns = "WUserId",
        onDelete = 1)})
public class World {

    @PrimaryKey(autoGenerate = true)
    public int worldId;

    //@ForeignKey user who created it
    public String WUserId;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo (name = "description")
    @NonNull
    public String description;

    @ColumnInfo (name = "rating")
    @NonNull
    public int rating;

    @ColumnInfo (name = "public")
    @NonNull
    public String privacy;

    public World(
            @NonNull String WUserId,
            @NonNull String name,
            @NonNull String description,
            @NonNull int rating,
            @NonNull String privacy)
    {
        this.WUserId = WUserId;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.privacy = privacy;
    }

}

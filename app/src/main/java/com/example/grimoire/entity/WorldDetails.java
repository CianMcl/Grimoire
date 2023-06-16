package com.example.grimoire.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (foreignKeys = {
        @ForeignKey(
                entity = User.class,
                parentColumns = "worldId",
                childColumns = "WUserId",
                onDelete = 1)})
public class WorldDetails {

    @PrimaryKey(autoGenerate = true)
    public int worldDetailsId;

    public int WUserId;

    @ColumnInfo(name = "note_name")
    @NonNull
    public String noteName;

    @ColumnInfo(name = "note_content")
    @NonNull
    public String noteContent;

    @ColumnInfo(name = "private")
    @NonNull
    public boolean noteAvailable;

    public WorldDetails(
            int WUserId,
            String noteName,
            String noteContent,
            boolean noteAvailable
    ){
        this.WUserId = WUserId;
        this.noteName = noteName;
        this.noteContent = noteContent;
        this.noteAvailable = noteAvailable;
    }

}

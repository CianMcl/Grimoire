package com.example.grimoire.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//import static android.arch.persistence.room.ForeignKey.Cascade;

@Entity (foreignKeys = {
        @ForeignKey(
                entity = user.class,
                parentColumns = "userId",
                childColumns = "CUserId",
                onDelete = 1
        ),
        @ForeignKey(
                entity = character.class,
                parentColumns = "templateId",
                childColumns = "CTemplateId",
                onDelete = 1
        )
})
public class character {

    @PrimaryKey (autoGenerate = true)
    public int characterId;

    public int CUserId;

    public int CTemplateId;

    @ColumnInfo (name = "first_name")
    @NonNull
    public String firstName;

    @ColumnInfo (name = "last_name")
    @NonNull
    public String lastName;

    @ColumnInfo (name = "public")
    @NonNull
    public boolean publicAvailable;

    public character(@NonNull int CUserId,
                     @NonNull int CTemplateId,
                     @NonNull String firstName,
                     @NonNull String lastName,
                     @NonNull boolean publicAvailable){
        this.CUserId = CUserId;
        this.CTemplateId = CTemplateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.publicAvailable = publicAvailable;
    }

}

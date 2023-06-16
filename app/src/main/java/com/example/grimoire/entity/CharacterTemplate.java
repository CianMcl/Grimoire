package com.example.grimoire.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//import static android.arch.persistence.room.ForeignKey.Cascade;

@Entity (foreignKeys = @ForeignKey(
        entity = User.class,
        parentColumns = "userId",
        childColumns = "CTUserId",
        onDelete = 1))
public class CharacterTemplate {

    @PrimaryKey (autoGenerate = true)
    public int templateId;

    public int CTUserId;

    //apparently room supports bool, so I'll test it here anyway
    //https://stackoverflow.com/questions/51130777/does-room-database-support-boolean-variables-in-entity
    @ColumnInfo (name = "listed_publicly") //1 or 0, y or n
    @NonNull
    public boolean listedPublic;

    //attribute 1 must be filled
    @ColumnInfo (name = "attribute_1_name")
    @NonNull
    public String attribute1;

    @ColumnInfo (name = "attribute_1_type")
    @NonNull
    public String attribute1Type;

    @ColumnInfo (name = "attribute_1_value")
    @NonNull
    public String attribute1val;

    //attribute
    @ColumnInfo (name = "attribute_2_name")
    public String attribute2;

    @ColumnInfo (name = "attribute_2_type")
    public String attribute2Type;

    @ColumnInfo (name = "attribute_2_value")
    public String attribute2val;

    //attribute
    @ColumnInfo (name = "attribute_3_name")
    public String attribute3;

    @ColumnInfo (name = "attribute_3_type")
    public String attribute3Type;

    @ColumnInfo (name = "attribute_3_value")
    public String attribute3val;

    //attribute
    @ColumnInfo (name = "attribute_4_name")
    public String attribute4;

    @ColumnInfo (name = "attribute_4_type")
    public String attribute4Type;

    @ColumnInfo (name = "attribute_4_value")
    public String attribute4val;

    //attribute
    @ColumnInfo (name = "attribute_5_name")
    public String attribute5;

    @ColumnInfo (name = "attribute_5_type")
    public String attribute5Type;

    @ColumnInfo (name = "attribute_5_value")
    public String attribute5val;

    //attribute
    @ColumnInfo (name = "attribute_6_name")
    public String attribute6;

    @ColumnInfo (name = "attribute_6_type")
    public String attribute6Type;

    @ColumnInfo (name = "attribute_6_value")
    public String attribute6val;

    //attribute
    @ColumnInfo (name = "attribute_7_name")
    public String attribute7;

    @ColumnInfo (name = "attribute_7_type")
    public String attribute7Type;

    @ColumnInfo (name = "attribute_7_value")
    public String attribute7val;

    //attribute
    @ColumnInfo (name = "attribute_8_name")
    public String attribute8;

    @ColumnInfo (name = "attribute_8_type")
    public String attribute8Type;

    @ColumnInfo (name = "attribute_8_value")
    public String attribute8val;

    //attribute
    @ColumnInfo (name = "attribute_9_name")
    public String attribute9;

    @ColumnInfo (name = "attribute_9_type")
    public String attribute9Type;

    @ColumnInfo (name = "attribute_9_value")
    public String attribute9val;

    //attribute
    @ColumnInfo (name = "attribute_10_name")
    public String attribute10;

    @ColumnInfo (name = "attribute_10_type")
    public String attribute10Type;

    @ColumnInfo (name = "attribute_10_value")
    public String attribute10val;

    //attribute
    @ColumnInfo (name = "attribute_11_name")
    public String attribute11;

    @ColumnInfo (name = "attribute_11_type")
    public String attribute11Type;

    @ColumnInfo (name = "attribute_11_value")
    public String attribute11val;

    //attribute
    @ColumnInfo (name = "attribute_12_name")
    public String attribute12;

    @ColumnInfo (name = "attribute_12_type")
    public String attribute12Type;

    @ColumnInfo (name = "attribute_12_value")
    public String attribute12val;

    //attribute
    @ColumnInfo (name = "attribute_13_name")
    public String attribute13;

    @ColumnInfo (name = "attribute_13_type")
    public String attribute13Type;

    @ColumnInfo (name = "attribute_13_value")
    public String attribute13val;

    //attribute
    @ColumnInfo (name = "attribute_14_name")
    public String attribute14;

    @ColumnInfo (name = "attribute_14_type")
    public String attribute14Type;

    @ColumnInfo (name = "attribute_14_value")
    public String attribute14val;

    //attribute
    @ColumnInfo (name = "attribute_15_name")
    public String attribute15;

    @ColumnInfo (name = "attribute_15_type")
    public String attribute15Type;

    @ColumnInfo (name = "attribute_15_value")
    public String attribute15val;

    //attribute
    @ColumnInfo (name = "attribute_16_name")
    public String attribute16;

    @ColumnInfo (name = "attribute_16_type")
    public String attribute16Type;

    @ColumnInfo (name = "attribute_16_value")
    public String attribute16val;

    //attribute
    @ColumnInfo (name = "attribute_17_name")
    public String attribute17;

    @ColumnInfo (name = "attribute_17_type")
    public String attribute17Type;

    @ColumnInfo (name = "attribute_17_value")
    public String attribute17val;

    //attribute
    @ColumnInfo (name = "attribute_18_name")
    public String attribute18;

    @ColumnInfo (name = "attribute_18_type")
    public String attribute18Type;

    @ColumnInfo (name = "attribute_18_value")
    public String attribute18val;

    //attribute
    @ColumnInfo (name = "attribute_19_name")
    public String attribute19;

    @ColumnInfo (name = "attribute_19_type")
    public String attribute19Type;

    @ColumnInfo (name = "attribute_19_value")
    public String attribute19val;

    //attribute
    @ColumnInfo (name = "attribute_20_name")
    public String attribute20;

    @ColumnInfo (name = "attribute_20_type")
    public String attribute20Type;

    @ColumnInfo (name = "attribute_20_value")
    public String attribute20val;

    /*template
    //attribute
    @ColumnInfo (name = "attribute__name")
    public String attribute;

    @ColumnInfo (name = "attribute__type")
    public String attributeType;

    @ColumnInfo (name = "attribute__value")
    public String attributeval;
    */


    public CharacterTemplate(int CTUserId,
                             boolean listedPublic,
                             String attribute1,
                             String attribute1Type,
                             String attribute1val,
                             String attribute2,
                             String attribute2Type,
                             String attribute2val,
                             String attribute3,
                             String attribute3Type,
                             String attribute3val,
                             String attribute4,
                             String attribute4Type,
                             String attribute4val,
                             String attribute5,
                             String attribute5Type,
                             String attribute5val,
                             String attribute6,
                             String attribute6Type,
                             String attribute6val,
                             String attribute7,
                             String attribute7Type,
                             String attribute7val,
                             String attribute8,
                             String attribute8Type,
                             String attribute8val,
                             String attribute9,
                             String attribute9Type,
                             String attribute9val,
                             String attribute10,
                             String attribute10Type,
                             String attribute10val,
                             String attribute11,
                             String attribute11Type,
                             String attribute11val,
                             String attribute12,
                             String attribute12Type,
                             String attribute12val,
                             String attribute13,
                             String attribute13Type,
                             String attribute13val,
                             String attribute14,
                             String attribute14Type,
                             String attribute14val,
                             String attribute15,
                             String attribute15Type,
                             String attribute15val,
                             String attribute16,
                             String attribute16Type,
                             String attribute16val,
                             String attribute17,
                             String attribute17Type,
                             String attribute17val,
                             String attribute18,
                             String attribute18Type,
                             String attribute18val,
                             String attribute19,
                             String attribute19Type,
                             String attribute19val,
                             String attribute20,
                             String attribute20Type,
                             String attribute20val)
    {
        this.CTUserId = CTUserId;
        this.listedPublic = listedPublic;
        this.attribute1 = attribute1;
        this.attribute1Type = attribute1Type;
        this.attribute1val = attribute1val;
        this.attribute2 = attribute2;
        this.attribute2Type = attribute2Type;
        this.attribute2val = attribute2val;
        this.attribute3 = attribute3;
        this.attribute3Type = attribute3Type;
        this.attribute3val = attribute3val;
        this.attribute4 = attribute4;
        this.attribute4Type = attribute4Type;
        this.attribute4val = attribute4val;
        this.attribute5 = attribute5;
        this.attribute5Type = attribute5Type;
        this.attribute5val = attribute5val;
        this.attribute6 = attribute6;
        this.attribute6Type = attribute6Type;
        this.attribute6val = attribute6val;
        this.attribute7 = attribute7;
        this.attribute7Type = attribute7Type;
        this.attribute7val = attribute7val;
        this.attribute8 = attribute8;
        this.attribute8Type = attribute8Type;
        this.attribute8val = attribute8val;
        this.attribute9 = attribute9;
        this.attribute9Type = attribute9Type;
        this.attribute9val = attribute9val;
        this.attribute10 = attribute10;
        this.attribute10Type = attribute10Type;
        this.attribute10val = attribute10val;
        this.attribute11 = attribute11;
        this.attribute11Type = attribute11Type;
        this.attribute11val = attribute11val;
        this.attribute12 = attribute12;
        this.attribute12Type = attribute12Type;
        this.attribute12val = attribute12val;
        this.attribute13 = attribute13;
        this.attribute13Type = attribute13Type;
        this.attribute13val = attribute13val;
        this.attribute14 = attribute14;
        this.attribute14Type = attribute14Type;
        this.attribute14val = attribute14val;
        this.attribute15 = attribute15;
        this.attribute15Type = attribute15Type;
        this.attribute15val = attribute15val;
        this.attribute16 = attribute16;
        this.attribute16Type = attribute16Type;
        this.attribute16val = attribute16val;
        this.attribute17 = attribute17;
        this.attribute17Type = attribute17Type;
        this.attribute17val = attribute17val;
        this.attribute18 = attribute18;
        this.attribute18Type = attribute18Type;
        this.attribute18val = attribute18val;
        this.attribute19 = attribute19;
        this.attribute19Type = attribute19Type;
        this.attribute19val = attribute19val;
        this.attribute20 = attribute20;
        this.attribute20Type = attribute20Type;
        this.attribute20val = attribute20val;

    }

}
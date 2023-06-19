package com.example.grimoire.recycle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grimoire.R;

//code from https://medium.com/swlh/create-recyclerview-in-android-fragment-c0f0b151125f
public class NewRecyclerViewHolder  extends RecyclerView.ViewHolder {

    private TextView worldId;
    private TextView worldName;
    private TextView worldDescription;

    public NewRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        worldId = itemView.findViewById(R.id.worldId);
        worldName = itemView.findViewById(R.id.worldName);
        worldDescription = itemView.findViewById(R.id.worldDescription);
    }

    //public TextView getView(){return view;    }

    public TextView getWorldId()
    {
        return worldId;
    }

    public TextView getWorldName(){
        return worldName;
    }

    public TextView getWorldDescription(){
        return worldDescription;
    }
}
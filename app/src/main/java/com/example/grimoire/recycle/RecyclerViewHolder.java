package com.example.grimoire.recycle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grimoire.R;

//code from https://medium.com/swlh/create-recyclerview-in-android-fragment-c0f0b151125f

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView view;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.randomText);
    }

    public TextView getView(){
        return view;
    }
}
package com.myapplication.mvvmsample.Demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.mvvmsample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyceleAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    public ArrayList<Integer> arrayList;

    public RecyceleAdapter(ArrayList<Integer> arrayList)
    {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

        Picasso.get().load(arrayList.get(position)).fit().into(holder.image);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

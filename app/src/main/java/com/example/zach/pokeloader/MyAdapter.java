package com.example.zach.pokeloader;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Pokemon> mData;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView id;
        TextView height;
        TextView weight;
        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.nameView);
            id = v.findViewById(R.id.idView);
            height = v.findViewById(R.id.heightView);
            weight = v.findViewById(R.id.weightView);
        }
    }

    public MyAdapter(ArrayList<Pokemon> data) {
        mData = data;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_ui_object, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(mData.get(position).name);
        holder.id.setText(((Integer)mData.get(position).id).toString());
        holder.height.setText(((Integer)mData.get(position).height).toString());
        holder.weight.setText(((Integer)mData.get(position).weight).toString());
    }

    public int getItemCount() {
        return mData.size();
    }
}

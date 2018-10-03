package com.example.zach.cs196fall2018;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Person[] mData;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mName;
        TextView mEmail;
        TextView mPhone;
        TextView mAddress;
        public MyViewHolder(View v) {
            super(v);
            mName = v.findViewById(R.id.textView2);
            mEmail = v.findViewById(R.id.textView3);
            mPhone = v.findViewById(R.id.textView4);
            mAddress = v.findViewById(R.id.textView5);

        }

    }

    public MyAdapter(Person[] data) {
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
        holder.mName.setText(mData[position].getName());
        holder.mEmail.setText(mData[position].getEmail());
        holder.mPhone.setText(mData[position].getPhone());
        holder.mAddress.setText(mData[position].getAddress());
    }

    public int getItemCount() {
        return mData.length;
    }
}

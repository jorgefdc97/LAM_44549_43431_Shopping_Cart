package com.example.lam_44549_43431_shopping_cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Product>  mDataset;

    public MyAdapter(List<Product> mDataset) {

        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_view_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.description.setText(mDataset.get(position).getDescription());
        holder.quantity.setText(new Integer(mDataset.get(position).getQuantity()).toString());
        holder.bought.setActivated(mDataset.get(position).getBought());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        TextView quantity;
        CheckBox bought;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description_textview);
            quantity = (TextView) itemView.findViewById(R.id.quantity_textview);
            bought = (CheckBox) itemView.findViewById(R.id.bought_checkbox);
        }
    }
}


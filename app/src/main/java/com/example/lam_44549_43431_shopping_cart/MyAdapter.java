package com.example.lam_44549_43431_shopping_cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Contact>  mDataset;

    public MyAdapter(List<Contact> mDataset) {

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
        holder.id.setText(new Integer(mDataset.get(position).getId()).toString());
        holder.nome.setText(mDataset.get(position).getNome());
        holder.morada.setText(mDataset.get(position).getMorada());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView nome;
        TextView morada;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textViewId);
            nome = (TextView) itemView.findViewById(R.id.textViewNome);
            morada = (TextView) itemView.findViewById(R.id.textViewMorada);

        }
    }
}


package com.example.a2smd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<Item> items;

    public Adapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item currentItem = items.get(position);

        holder.name.setText(currentItem.getName());
        holder.location.setText(currentItem.getLocation());
        holder.description.setText(currentItem.getDescription());
        holder.phone.setText(currentItem.getPhone());
        holder.rating.setText(currentItem.getRating());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

package com.example.a2smd;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView location;
    public TextView description;
    public TextView phone;
    public TextView rating;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.res_name);
        location = itemView.findViewById(R.id.res_location);
        description = itemView.findViewById(R.id.res_description);
        phone = itemView.findViewById(R.id.res_phone);
        rating = itemView.findViewById(R.id.res_rating);
    }
}

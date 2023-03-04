package com.example.fetchapplication.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetchapplication.R;

public class ItemsViewHolder extends RecyclerView.ViewHolder {

    private final TextView itemIdTextView;
    private final TextView itemListIdTextView;
    private final TextView itemNameTextView;

    public ItemsViewHolder(@NonNull View itemView) {
        super(itemView);
        itemIdTextView = itemView.findViewById(R.id.itemId);
        itemListIdTextView = itemView.findViewById(R.id.itemListId);
        itemNameTextView = itemView.findViewById(R.id.itemName);
    }

    public void setItem(int itemId, int itemListId, @NonNull String itemName) {
        itemIdTextView.setText(String.valueOf(itemId));
        itemListIdTextView.setText(String.valueOf(itemListId));
        itemNameTextView.setText(itemName);
    }
}

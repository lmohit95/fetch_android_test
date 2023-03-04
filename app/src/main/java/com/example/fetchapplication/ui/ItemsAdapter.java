package com.example.fetchapplication.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetchapplication.R;
import com.example.fetchapplication.data.Item;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {

    @NonNull
    private List<Item> items;

    ItemsAdapter(@NonNull List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        Item item = items.get(position);
        holder.setItem(item.id, item.listId, item.name);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateAdapter(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}

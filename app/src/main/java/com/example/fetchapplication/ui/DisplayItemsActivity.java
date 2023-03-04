package com.example.fetchapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fetchapplication.R;
import com.example.fetchapplication.data.Item;
import com.example.fetchapplication.di.component.DaggerDisplayItemsComponent;
import com.example.fetchapplication.repository.DisplayItemsRepository;
import com.example.fetchapplication.utils.Utils;
import com.example.fetchapplication.viewmodel.DisplayItemsViewModel;
import com.example.fetchapplication.viewmodel.MyViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DisplayItemsActivity extends AppCompatActivity {

    private static final String TAG = DisplayItemsViewModel.class.getCanonicalName();

    private DisplayItemsViewModel displayItemsViewModel;
    private RecyclerView itemsRecyclerView;
    private ItemsAdapter itemsAdapter;
    private LinearLayout itemsLayout;
    private TextView loadingView;
    private List<Item> items;

    @Inject
    DisplayItemsRepository displayItemsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);

        inject();
        init();
        observeItems();
    }

    private void init() {
        Log.d(TAG, "init");
        // Initializing viewmodel
        displayItemsViewModel = new ViewModelProvider(this,
                new MyViewModelFactory(displayItemsRepository))
                .get(DisplayItemsViewModel.class);

        // Initializing recycler view
        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);
        loadingView = findViewById(R.id.loading);
        itemsLayout = findViewById(R.id.itemsLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        itemsRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(itemsRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        itemsRecyclerView.addItemDecoration(dividerItemDecoration);
        items = new ArrayList<>();
    }

    private void observeItems() {
        displayItemsViewModel.getItemsFromSource();
        displayItemsViewModel.getItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> receivedItems) {
                if (Utils.isListNotEmpty(receivedItems)) {
                    items.addAll(receivedItems);
                    displayItemsLayout();
                    updateAdapter(items);
                }
            }
        });
    }

    private void displayItemsLayout() {
        loadingView.setVisibility(View.GONE);
        itemsLayout.setVisibility(View.VISIBLE);
        itemsRecyclerView.setVisibility(View.VISIBLE);
    }

    private void updateAdapter(List<Item> items) {
        if (itemsAdapter == null) {
            itemsAdapter = new ItemsAdapter(items);
            itemsRecyclerView.setAdapter(itemsAdapter);
        } else {
            // Will not be called in current application
            itemsAdapter.updateAdapter(items);
        }
    }

    private void inject() {
        DaggerDisplayItemsComponent.create().inject(this);
    }
}
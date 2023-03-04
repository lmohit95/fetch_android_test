package com.example.fetchapplication.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fetchapplication.data.Item;
import com.example.fetchapplication.network.NetworkConnection;
import com.example.fetchapplication.repository.DisplayItemsRepository;
import com.example.fetchapplication.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DisplayItemsViewModel extends ViewModel {

    private static final String TAG = DisplayItemsViewModel.class.getCanonicalName();
    private static final String ITEM_STRING = "Item";
    private MutableLiveData<List<Item>> itemData;
    private final DisplayItemsRepository displayItemsRepository;

    public DisplayItemsViewModel(DisplayItemsRepository displayItemsRepository) {
        this.displayItemsRepository = displayItemsRepository;
    }

    public LiveData<List<Item>> getItems() {
        if (itemData == null) {
            itemData = new MutableLiveData<>();
        }
        return itemData;
    }

    /**
     * Gets Single<List<Item>> data from data repository and applies RxJava subscribers and observers
     */
    public void getItemsFromSource() {
        displayItemsRepository.getItems(NetworkConnection.getNetworkService())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Item>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // no-op
                    }

                    @Override
                    public void onSuccess(List<Item> items) {
                        transformData(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * Transforms data by removing null/empty strings and sorting them based on
     * listId and name
     *
     * @param items List of items received from data repository
     */
    private void transformData(List<Item> items) {
        if (Utils.isListNotEmpty(items)) {
            Log.d(TAG, "Empty list received from server");
        }
        List<Item> updatedItems = new ArrayList<>();
        for (Item item : items) {
            if (!TextUtils.isEmpty(item.name)) {
                item.name = item.name.trim();
                if (isValid(item.name)) {
                    updatedItems.add(item);
                }
            }
        }
        Collections.sort(updatedItems);
        itemData.setValue(updatedItems);
    }

    /**
     * Check whether item name is a valid string.
     * @param name item name received from the server
     * @return true/false based on validity of the item name
     */
    private boolean isValid(String name) {
        String[] nameComponents = name.split("\\s+");
        if (nameComponents.length != 2 || !ITEM_STRING.equals(nameComponents[0])) {
            Log.d(TAG, "IsValid : corrupt data received from server" + name);
            return false;
        }
        try {
            Integer.parseInt(nameComponents[nameComponents.length - 1]);
        } catch (NumberFormatException e) {
            Log.d(TAG, e.getMessage());
            return false;
        }
        return true;
    }
}

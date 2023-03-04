package com.example.fetchapplication.repository;

import androidx.annotation.NonNull;

import com.example.fetchapplication.data.Item;
import com.example.fetchapplication.network.NetworkService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class DisplayItemsRepository {

    @Inject
    DisplayItemsRepository() {
        // no-op
    }

    /**
     * Gets list of items from server
     * @param networkService Retrofit library object used to communicated with server
     * @return list of Item objects
     */
    public Single<List<Item>> getItems(@NonNull NetworkService networkService) {
        return networkService.getItemList();
    }
}

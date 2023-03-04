package com.example.fetchapplication.network;

import com.example.fetchapplication.data.Item;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface NetworkService {

    /* Using single, since only 1 list is being returned currently.
       We can use Observable, Flowable for continuous flow of data.
    */
    @GET("hiring.json")
    Single<List<Item>> getItemList();
}

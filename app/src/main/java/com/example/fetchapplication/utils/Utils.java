package com.example.fetchapplication.utils;

import com.example.fetchapplication.data.Item;

import java.util.List;

public class Utils {

    public static boolean isListNotEmpty(List<Item> list) {
        return list != null && !list.isEmpty();
    }
}

package com.example.fetchapplication.data;

import com.google.gson.annotations.SerializedName;

public class Item implements Comparable<Item> {
    @SerializedName("id")
    public int id;

    @SerializedName("listId")
    public int listId;

    @SerializedName("name")
    public String name;

    @Override
    public int compareTo(Item item) {
        if (listId > item.listId) return 1;
        else if (listId == item.listId) {
            // Comparing numbers of the item names. Direct comparison using compareTo() results in
            // incorrect comparisons
            String[] itemNameArr1Name = name.split("\\s+");
            String[] itemNameArr2Name = item.name.split("\\s+");
            int item1Num = Integer.parseInt(itemNameArr1Name[itemNameArr1Name.length - 1]);
            int item2Num = Integer.parseInt(itemNameArr2Name[itemNameArr2Name.length - 1]);
            if (item1Num > item2Num) return 1;
        }
        return -1;
    }
}

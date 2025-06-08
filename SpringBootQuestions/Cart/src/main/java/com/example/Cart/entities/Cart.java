package com.example.Cart.entities;

import java.util.HashMap;
import java.util.Map;


public class Cart {
    public Map<Item, CartItem> getItemtoCartItem() {
        return itemtoCartItem;
    }

    Map<Item, CartItem> itemtoCartItem = new HashMap<>();

    @Override
    public String toString() {
        return itemtoCartItem.entrySet().stream().map(entry -> entry.getValue()).toList().toString();
    }
}

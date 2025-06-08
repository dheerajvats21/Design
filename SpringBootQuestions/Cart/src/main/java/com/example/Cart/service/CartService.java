package com.example.Cart.service;

import com.example.Cart.entities.Cart;

public interface CartService {
    void addItem(String itemID, String userID, Integer quantity) throws Exception; // adds quantity amount to cart
    Cart viewCart(String userID);
    void removeItem(String userID, String itemId); // removes completely
    void clearCart(String userID);
    void updateItemQuantity(String userID, String itemId, Integer quantity);
}

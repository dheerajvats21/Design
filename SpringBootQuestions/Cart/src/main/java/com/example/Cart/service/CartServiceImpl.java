package com.example.Cart.service;

import com.example.Cart.entities.Cart;
import com.example.Cart.entities.Item;
import com.example.Cart.entities.User;
import com.example.Cart.repos.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final DataBase dataBase;

    @Autowired
    public CartServiceImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void addItem(String itemID, String userID, Integer quantity) throws Exception{
        Item item = dataBase.getItem(itemID);
        User user = dataBase.getUser(userID);
        if (user != null && item != null) dataBase.addItemForUser(item, user, quantity);
        else {
            throw new Exception("wrong item or user " + userID + " , " + itemID);
        }
    }

    @Override
    public Cart viewCart(String userID) {
        User user = dataBase.getUser(userID);
        if (user != null) {
            return dataBase.getCart(user);
        }
        return null;
    }

    @Override
    public void removeItem(String userID, String itemId) {
        Item item = dataBase.getItem(itemId);
        User user = dataBase.getUser(userID);
        if (user != null && item != null) dataBase.removeItemForUser(item, user);
    }

    @Override
    public void clearCart(String userID) {
        User user = dataBase.getUser(userID);
        if (user != null) {
            dataBase.clearCart(user);
        }
    }

    @Override
    public void updateItemQuantity(String userID, String itemId, Integer quantity) {
        Item item = dataBase.getItem(itemId);
        User user = dataBase.getUser(userID);
        if (user != null && item != null) dataBase.updateItemForUser(item, user, quantity);
    }
}

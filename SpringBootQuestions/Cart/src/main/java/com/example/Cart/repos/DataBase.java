package com.example.Cart.repos;

import com.example.Cart.entities.Cart;
import com.example.Cart.entities.Item;
import com.example.Cart.entities.User;
import org.springframework.stereotype.Component;

@Component
public interface DataBase {

    void addItem(Item item);
    Item removeItem(Item item);
    Item getItem(String itemID);
    void addUser(User user);
    User removeUser(User user);
    User getUser(String user);

    void addItemForUser(Item item, User user, Integer quantity);
    void removeItemForUser(Item item, User user);

    void clearCart(User user);
    Cart getCart(User user);

    void updateItemForUser(Item item, User user, Integer quantity);


}

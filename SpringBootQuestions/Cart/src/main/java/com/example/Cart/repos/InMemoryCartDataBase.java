package com.example.Cart.repos;

import com.example.Cart.entities.Cart;
import com.example.Cart.entities.CartItem;
import com.example.Cart.entities.Item;
import com.example.Cart.entities.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryCartDataBase implements DataBase {

    Map<String, Item> itemsMap = new ConcurrentHashMap<>();
    Map<String, User> userMap = new ConcurrentHashMap<>();
    Map<User, Cart> userToCart = new ConcurrentHashMap<>();

    @Override
    public void addItem(Item item) {
        String id = item.getId();
        if (id == null) {
            item.setId(UUID.randomUUID().toString());

        } else {
           if (itemsMap.containsKey(id)) return;
        }
        itemsMap.putIfAbsent(item.getId(), item);
        System.out.println("item added with id = " + item.getId());
    }

    @Override
    public Item removeItem(Item item) {
        String id = item.getId();
        if (id != null) {
            return itemsMap.remove(id);
        }
        return null;
    }

    @Override
    public Item getItem(String itemID) {
        return itemsMap.getOrDefault(itemID, null);
    }

    @Override
    public void addUser(User user) {
        String id = user.getId();
        if (id == null) {
            user.setId(UUID.randomUUID().toString());
        } else {
            if (userMap.containsKey(id)) return;
        }
        userMap.putIfAbsent(user.getId(), user);
        System.out.println("User added with id = " + user.getId());
    }

    @Override
    public User removeUser(User user) {
        String id = user.getId();
        if (id != null) {
            return userMap.remove(id);
        }
        return null;
    }

    @Override
    public User getUser(String userId) {
        return userMap.getOrDefault(userId, null);
    }

    @Override
    public void addItemForUser(Item item, User user, Integer quantity) {
        if (!itemsMap.containsKey(item.getId()) || !userMap.containsKey(user.getId())) return;

        userToCart.compute(user, (k, v) -> {
            if (v == null) {
                v = new Cart();
            }
            Map<Item, CartItem> itemsToCartItem = v.getItemtoCartItem();

            itemsToCartItem.putIfAbsent(item, CartItem.builder().item(item).build());
            CartItem cartItem = itemsToCartItem.get(item);
            cartItem.setQuantity(cartItem.getQuantity() + (quantity == null ? 1 : quantity));
            return v;
        });
        return;
    }

    @Override
    public void removeItemForUser(Item item, User user) {
        if (!itemsMap.containsKey(item.getId()) || !userMap.containsKey(user.getId())
                || !userToCart.containsKey(user)) return;
        userToCart.compute(user, (k, v) -> {
            if (v != null) v.getItemtoCartItem().remove(item);
            return v;
        });
    }

    @Override
    public void clearCart(User user) {
        if (!userMap.containsKey(user.getId())
                || !userToCart.containsKey(user)) return;
        userToCart.remove(user);
    }

    @Override
    public Cart getCart(User user) {
        if (!userMap.containsKey(user.getId())
                || !userToCart.containsKey(user)) return null;
        return userToCart.get(user);
    }

    @Override
    public void updateItemForUser(Item item, User user, Integer quantity) {
        if (!itemsMap.containsKey(item.getId()) || !userMap.containsKey(user.getId()) || quantity == null) return;

        userToCart.compute(user, (k, v) -> {
            if (v == null) {
                v = new Cart();
            }
            Map<Item, CartItem> itemsToCartItem = v.getItemtoCartItem();

            itemsToCartItem.putIfAbsent(item, CartItem.builder().item(item).build());
            CartItem cartItem = itemsToCartItem.get(item);
            cartItem.setQuantity(quantity);
            return v;
        });
    }
}

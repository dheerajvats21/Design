package com.example.Cart.entities;

import lombok.Builder;

@Builder
public class CartItem {
    Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    int quantity;

    @Override
    public String toString() {
        return "item = " + item.getId() + " quantity = " + quantity;
    }
}

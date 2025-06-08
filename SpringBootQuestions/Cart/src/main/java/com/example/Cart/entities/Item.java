package com.example.Cart.entities;

import lombok.Builder;

import java.util.UUID;

@Builder
public class Item {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    private String name;
    private Integer price;


}

package com.example.Cart.entities;

import lombok.Builder;

@Builder
public class User {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    String name;
    Integer age;

}

package com.example.Cart.dtos;

import lombok.Builder;

@Builder
public class ItemAddedDto {
    public String getResponse() {
        return response;
    }

    private String response;
}

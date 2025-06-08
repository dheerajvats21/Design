package com.example.Cart.controller;

import com.example.Cart.dtos.ItemAddedDto;
import com.example.Cart.entities.Cart;
import com.example.Cart.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/additem")
    public ResponseEntity<ItemAddedDto> addItem(@RequestParam String userId,
                                                @RequestParam String itemId,
                                                @RequestParam(required = false) Integer quantity) throws Exception {
        cartService.addItem(itemId, userId, quantity);
        return ResponseEntity.ok(ItemAddedDto.builder()
                .response("item added successfully")
                .build());
    }

    @GetMapping("getcart/{userID}")
    public ResponseEntity<String> viewCart(@PathVariable String userID) {
        Cart cart = cartService.viewCart(userID);
        return ResponseEntity.ok(cart != null ? cart.toString(): "Empty Cart");
    }

    @DeleteMapping("removeitem/{userID}/{itemID}")
    public ResponseEntity<String> removeItem(@PathVariable String userID, @PathVariable String itemID) {
        cartService.removeItem(userID, itemID);
        return ResponseEntity.ok("removed");
    }

    @DeleteMapping("clearcart/{userID}")
    public ResponseEntity<String> clearCart(@PathVariable String userID) {
        cartService.clearCart(userID);
        return ResponseEntity.ok("cleared");
    }

    @PutMapping("/updatequantity/{userId}/{itemId}/{quantity}")
    public ResponseEntity<String> updateItemQuantity(@PathVariable String userId,
                                                     @PathVariable String itemId,
                                                     @PathVariable Integer quantity) {
        cartService.updateItemQuantity(userId, itemId, quantity);
        return ResponseEntity.ok("done");
    }

}

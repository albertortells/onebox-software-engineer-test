package com.onebox.cart.controller;

import com.onebox.cart.model.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
public class CartController {

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart cart = new Cart(UUID.randomUUID().toString());
        return ResponseEntity.created(URI.create("/carts/" + cart.id())).body(cart);
    }
}

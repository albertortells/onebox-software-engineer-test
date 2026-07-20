package com.onebox.cart.service;

import com.onebox.cart.model.Cart;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public Cart createCart() {
        return new Cart(UUID.randomUUID().toString());
    }
}

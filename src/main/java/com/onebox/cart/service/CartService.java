package com.onebox.cart.service;

import com.onebox.cart.model.Cart;

public interface CartService {

    Cart createCart();

    Cart getCart(String cartId);
}

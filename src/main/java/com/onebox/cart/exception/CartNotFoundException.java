package com.onebox.cart.exception;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(String cartId) {
        super("Cart not found: " + cartId);
    }
}

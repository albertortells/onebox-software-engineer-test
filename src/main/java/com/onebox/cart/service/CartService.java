package com.onebox.cart.service;

import com.onebox.cart.model.Cart;
import com.onebox.cart.model.Product;

import java.util.List;

public interface CartService {

    Cart createCart();

    Cart getCart(String cartId);

    Cart addProducts(String cartId, List<Product> products);

    void deleteCart(String cartId);
}

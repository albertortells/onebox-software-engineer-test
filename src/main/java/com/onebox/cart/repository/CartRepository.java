package com.onebox.cart.repository;

import com.onebox.cart.model.Cart;

public interface CartRepository {

    Cart save(Cart cart);
}

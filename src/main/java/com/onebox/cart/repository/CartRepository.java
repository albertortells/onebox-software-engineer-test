package com.onebox.cart.repository;

import com.onebox.cart.model.Cart;

import java.time.Duration;
import java.util.Optional;

public interface CartRepository {

    Cart save(Cart cart);

    Optional<Cart> findById(String id);

    void deleteById(String id);

    void deleteInactiveCartsOlderThan(Duration inactivity);
}

package com.onebox.cart.repository;

import com.onebox.cart.model.Cart;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCartRepository implements CartRepository {

    private final Map<String, Cart> carts = new ConcurrentHashMap<>();
    private final Map<String, Instant> lastAccessedAt = new ConcurrentHashMap<>();

    @Override
    public Cart save(Cart cart) {
        carts.put(cart.id(), cart);
        lastAccessedAt.put(cart.id(), Instant.now());
        return cart;
    }

    @Override
    public Optional<Cart> findById(String id) {
        Cart cart = carts.get(id);
        if (cart != null) {
            lastAccessedAt.put(id, Instant.now());
        }
        return Optional.ofNullable(cart);
    }

    @Override
    public void deleteById(String id) {
        carts.remove(id);
        lastAccessedAt.remove(id);
    }

    @Override
    public void deleteInactiveCartsOlderThan(Duration inactivity) {
        Instant threshold = Instant.now().minus(inactivity);
        lastAccessedAt.entrySet().removeIf(entry -> {
            if (entry.getValue().isBefore(threshold)) {
                carts.remove(entry.getKey());
                return true;
            }
            return false;
        });
    }
}

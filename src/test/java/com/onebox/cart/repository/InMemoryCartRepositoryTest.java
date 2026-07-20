package com.onebox.cart.repository;

import com.onebox.cart.model.Cart;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryCartRepositoryTest {

    private final InMemoryCartRepository repository = new InMemoryCartRepository();

    @Test
    void savesAndReturnsTheGivenCart() {
        Cart cart = new Cart("cart-1");

        Cart saved = repository.save(cart);

        assertThat(saved).isEqualTo(cart);
    }
}

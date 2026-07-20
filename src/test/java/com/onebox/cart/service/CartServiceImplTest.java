package com.onebox.cart.service;

import com.onebox.cart.model.Cart;
import com.onebox.cart.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Test
    void createsCartWithGeneratedIdAndSavesIt() {
        when(cartRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CartServiceImpl cartService = new CartServiceImpl(cartRepository);

        Cart cart = cartService.createCart();

        assertThat(cart.id()).isNotBlank();
        verify(cartRepository).save(cart);
    }
}

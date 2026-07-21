package com.onebox.cart.service;

import com.onebox.cart.exception.CartNotFoundException;
import com.onebox.cart.model.Cart;
import com.onebox.cart.model.Product;
import com.onebox.cart.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void getsCartWhenItExists() {
        Cart cart = new Cart("cart-1", List.of());
        when(cartRepository.findById("cart-1")).thenReturn(Optional.of(cart));

        CartServiceImpl cartService = new CartServiceImpl(cartRepository);

        assertThat(cartService.getCart("cart-1")).isEqualTo(cart);
    }

    @Test
    void throwsWhenGettingAMissingCart() {
        when(cartRepository.findById("missing")).thenReturn(Optional.empty());

        CartServiceImpl cartService = new CartServiceImpl(cartRepository);

        assertThatThrownBy(() -> cartService.getCart("missing"))
                .isInstanceOf(CartNotFoundException.class);
    }

    @Test
    void addsProductsWhenCartExists() {
        Product product = new Product(1L, "Laptop", new BigDecimal("999.99"));
        Cart existingCart = new Cart("cart-1", List.of());
        when(cartRepository.findById("cart-1")).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CartServiceImpl cartService = new CartServiceImpl(cartRepository);

        Cart updatedCart = cartService.addProducts("cart-1", List.of(product));

        assertThat(updatedCart.products()).containsExactly(product);
    }

    @Test
    void throwsWhenAddingProductsToAMissingCart() {
        when(cartRepository.findById("missing")).thenReturn(Optional.empty());

        CartServiceImpl cartService = new CartServiceImpl(cartRepository);

        assertThatThrownBy(() -> cartService.addProducts("missing", List.of()))
                .isInstanceOf(CartNotFoundException.class);
    }

    @Test
    void deletesCartWhenItExists() {
        Cart cart = new Cart("cart-1", List.of());
        when(cartRepository.findById("cart-1")).thenReturn(Optional.of(cart));

        CartServiceImpl cartService = new CartServiceImpl(cartRepository);

        cartService.deleteCart("cart-1");

        verify(cartRepository).deleteById("cart-1");
    }

    @Test
    void throwsWhenDeletingAMissingCart() {
        when(cartRepository.findById("missing")).thenReturn(Optional.empty());

        CartServiceImpl cartService = new CartServiceImpl(cartRepository);

        assertThatThrownBy(() -> cartService.deleteCart("missing"))
                .isInstanceOf(CartNotFoundException.class);
    }
}

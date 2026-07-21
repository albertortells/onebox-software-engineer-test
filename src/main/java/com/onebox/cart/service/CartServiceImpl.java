package com.onebox.cart.service;

import com.onebox.cart.exception.CartNotFoundException;
import com.onebox.cart.model.Cart;
import com.onebox.cart.model.Product;
import com.onebox.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart() {
        Cart cart = new Cart(UUID.randomUUID().toString(), List.of());
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(String cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
    }

    @Override
    public Cart addProducts(String cartId, List<Product> products) {
        Cart existingCart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        List<Product> updatedProducts = new ArrayList<>(existingCart.products());
        updatedProducts.addAll(products);
        Cart updatedCart = new Cart(cartId, updatedProducts);
        return cartRepository.save(updatedCart);
    }

    @Override
    public void deleteCart(String cartId) {
        cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        cartRepository.deleteById(cartId);
    }

    @Override
    public void expireInactiveCarts() {
        cartRepository.deleteInactiveCartsOlderThan(Duration.ofMinutes(10));
    }
}

package com.onebox.cart.service;

import com.onebox.cart.model.Cart;
import com.onebox.cart.model.Product;
import com.onebox.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

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
        return cartRepository.findById(cartId).orElse(null);
    }

    @Override
    public Cart addProducts(String cartId, List<Product> products) {
        return new Cart(cartId, products);
    }
}

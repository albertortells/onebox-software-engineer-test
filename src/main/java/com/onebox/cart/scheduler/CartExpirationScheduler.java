package com.onebox.cart.scheduler;

import com.onebox.cart.service.CartService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CartExpirationScheduler {

    private final CartService cartService;

    public CartExpirationScheduler(CartService cartService) {
        this.cartService = cartService;
    }

    @Scheduled(fixedRate = 60_000)
    public void expireInactiveCarts() {
        cartService.expireInactiveCarts();
    }
}

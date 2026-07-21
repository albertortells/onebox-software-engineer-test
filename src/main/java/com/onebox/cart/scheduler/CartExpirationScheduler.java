package com.onebox.cart.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CartExpirationScheduler {

    @Scheduled(fixedRate = 60_000)
    public void expireInactiveCarts() {
    }
}

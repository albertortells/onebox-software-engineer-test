package com.onebox.cart.scheduler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class CartExpirationSchedulerTest {

    private final CartExpirationScheduler scheduler = new CartExpirationScheduler();

    @Test
    void expiresInactiveCartsWithoutThrowing() {
        assertThatCode(scheduler::expireInactiveCarts).doesNotThrowAnyException();
    }
}

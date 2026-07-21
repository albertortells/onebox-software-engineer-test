package com.onebox.cart.scheduler;

import com.onebox.cart.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartExpirationSchedulerTest {

    @Mock
    private CartService cartService;

    @Test
    void delegatesToCartService() {
        CartExpirationScheduler scheduler = new CartExpirationScheduler(cartService);

        scheduler.expireInactiveCarts();

        verify(cartService).expireInactiveCarts();
    }
}

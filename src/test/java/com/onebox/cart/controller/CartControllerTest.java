package com.onebox.cart.controller;

import com.onebox.cart.model.Cart;
import com.onebox.cart.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CartService cartService;

    @Test
    void createsCartWithGeneratedId() throws Exception {
        when(cartService.createCart()).thenReturn(new Cart("cart-1"));

        mockMvc.perform(post("/carts"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/carts/cart-1"))
                .andExpect(jsonPath("$.id").value("cart-1"));
    }

    @Test
    void getsCartById() throws Exception {
        mockMvc.perform(get("/carts/cart-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("cart-1"));
    }
}

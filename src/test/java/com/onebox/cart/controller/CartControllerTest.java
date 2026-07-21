package com.onebox.cart.controller;

import com.onebox.cart.model.Cart;
import com.onebox.cart.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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
        when(cartService.createCart()).thenReturn(new Cart("cart-1", List.of()));

        mockMvc.perform(post("/carts"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/carts/cart-1"))
                .andExpect(jsonPath("$.id").value("cart-1"));
    }

    @Test
    void getsCartById() throws Exception {
        when(cartService.getCart("cart-1")).thenReturn(new Cart("cart-1", List.of()));

        mockMvc.perform(get("/carts/cart-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("cart-1"));
    }

    @Test
    void addsProductsToCart() throws Exception {
        String requestBody = """
                [
                  {"id": 1, "description": "Laptop", "amount": 999.99}
                ]
                """;

        mockMvc.perform(post("/carts/cart-1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("cart-1"))
                .andExpect(jsonPath("$.products[0].id").value(1))
                .andExpect(jsonPath("$.products[0].description").value("Laptop"))
                .andExpect(jsonPath("$.products[0].amount").value(999.99));
    }
}

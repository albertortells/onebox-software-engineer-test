package com.onebox.cart.model;

import java.util.List;

public record Cart(String id, List<Product> products) {
}

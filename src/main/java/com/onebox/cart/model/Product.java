package com.onebox.cart.model;

import java.math.BigDecimal;

public record Product(Long id, String description, BigDecimal amount) {
}

package com.codewithmosh.store.carts;

import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO for {@link CartItem}
 */
@Data
public class CartItemDto {
    private ProductDto product;
    private Integer quantity;
    private BigDecimal totalPrice;
}

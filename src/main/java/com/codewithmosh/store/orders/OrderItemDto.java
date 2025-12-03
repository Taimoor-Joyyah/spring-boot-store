package com.codewithmosh.store.orders;

import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO for {@link OrderItem}
 */
@Data
public class OrderItemDto {
    private ProductDto product;
    private Integer quantity;
    private BigDecimal totalPrice;
}

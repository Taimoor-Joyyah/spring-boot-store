package com.codewithmosh.store.orders;

import com.codewithmosh.store.payments.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link Order}
 */
@Data
public class OrderDto {
    private Long id;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    private Set<OrderItemDto> items;
}

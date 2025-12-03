package com.codewithmosh.store.payments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResult {
    private final Long orderId;
    private final PaymentStatus paymentStatus;
}

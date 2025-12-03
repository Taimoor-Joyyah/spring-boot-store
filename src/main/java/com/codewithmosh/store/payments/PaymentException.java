package com.codewithmosh.store.payments;

import com.codewithmosh.store.common.ApiException;
import org.springframework.http.HttpStatus;

public class PaymentException extends ApiException {
    public PaymentException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public PaymentException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

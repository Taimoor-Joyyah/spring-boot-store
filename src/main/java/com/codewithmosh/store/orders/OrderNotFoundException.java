package com.codewithmosh.store.orders;

import com.codewithmosh.store.common.ApiException;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends ApiException {
    public OrderNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }

    public OrderNotFoundException(String msg, HttpStatus status) {
        super(msg, status);
    }
}

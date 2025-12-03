package com.codewithmosh.store.carts;

import com.codewithmosh.store.common.ApiException;
import org.springframework.http.HttpStatus;

public class CartNotFoundException extends ApiException {
    public CartNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }

    public CartNotFoundException(String msg, HttpStatus status) {
        super(msg, status);
    }
}

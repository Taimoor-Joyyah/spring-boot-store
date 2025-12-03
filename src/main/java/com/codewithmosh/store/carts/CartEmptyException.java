package com.codewithmosh.store.carts;

import com.codewithmosh.store.common.ApiException;
import org.springframework.http.HttpStatus;

public class CartEmptyException extends ApiException {
    public CartEmptyException(String msg) {
        super(msg,  HttpStatus.BAD_REQUEST);
    }

    public CartEmptyException(String msg, HttpStatus status) {
        super(msg,  status);
    }
}

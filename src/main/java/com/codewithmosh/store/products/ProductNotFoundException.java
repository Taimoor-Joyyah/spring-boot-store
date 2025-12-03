package com.codewithmosh.store.products;

import com.codewithmosh.store.common.ApiException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ApiException {
    public ProductNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }

    public ProductNotFoundException(String msg, HttpStatus status) {
        super(msg, status);
    }
}

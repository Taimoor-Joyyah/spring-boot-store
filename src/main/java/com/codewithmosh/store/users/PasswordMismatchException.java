package com.codewithmosh.store.users;

import com.codewithmosh.store.common.ApiException;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends ApiException {
    public PasswordMismatchException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public PasswordMismatchException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

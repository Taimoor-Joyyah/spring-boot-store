package com.codewithmosh.store.users;

import com.codewithmosh.store.common.ApiException;
import org.springframework.http.HttpStatus;

public class RegistrationException extends ApiException {

    public RegistrationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public RegistrationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

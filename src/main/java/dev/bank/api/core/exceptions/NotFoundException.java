package dev.bank.api.core.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpRequestException {
    public NotFoundException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

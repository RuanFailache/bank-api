package dev.bank.api.core.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpRequestException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}

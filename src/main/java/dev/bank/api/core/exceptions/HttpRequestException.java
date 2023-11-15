package dev.bank.api.core.exceptions;

import org.springframework.http.HttpStatus;

public abstract class HttpRequestException extends Exception {
    protected HttpStatus status;

    protected HttpRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status.value();
    }
}

package dev.bank.api.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class HttpRequestException extends Exception {
    protected HttpStatus status;

    protected Object data;

    protected HttpRequestException(String message, HttpStatus status) {
        super(message);
        this.data = null;
        this.status = status;
    }

    protected HttpRequestException(Object data, String message, HttpStatus status) {
        super(message);
        this.data = data;
        this.status = status;
    }
}

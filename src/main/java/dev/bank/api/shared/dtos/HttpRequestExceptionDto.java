package dev.bank.api.shared.dtos;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class HttpRequestExceptionDto {
    private final Object data;
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;

    public HttpRequestExceptionDto(String message, HttpStatus status, Object data) {
        this.data = data;
        this.message = message;
        this.status = status.value();
        this.timestamp = LocalDateTime.now();
    }

    public HttpRequestExceptionDto(String message, HttpStatus status) {
        this.data = null;
        this.message = message;
        this.status = status.value();
        this.timestamp = LocalDateTime.now();
    }
}

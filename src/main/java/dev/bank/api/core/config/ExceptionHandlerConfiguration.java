package dev.bank.api.core.config;

import dev.bank.api.core.exceptions.HttpRequestException;
import dev.bank.api.core.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerConfiguration {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }

    @ExceptionHandler(HttpRequestException.class)
    public ResponseEntity<String> handleHttpRequestException(HttpRequestException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}

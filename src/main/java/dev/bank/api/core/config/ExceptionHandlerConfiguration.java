package dev.bank.api.core.config;

import dev.bank.api.core.dtos.HttpRequestExceptionDto;
import dev.bank.api.core.exceptions.HttpRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerConfiguration {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpRequestExceptionDto> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new HttpRequestExceptionDto(
                        e.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
        );
    }

    @ExceptionHandler(HttpRequestException.class)
    public ResponseEntity<HttpRequestExceptionDto> handleHttpRequestException(HttpRequestException e) {
        return ResponseEntity.status(e.getStatus()).body(
                new HttpRequestExceptionDto(
                        e.getMessage(),
                        e.getStatus()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpRequestExceptionDto> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        Map<String, String> errors = new HashMap<>();
        fieldErrors.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(
                new HttpRequestExceptionDto(
                        "DTO validation failed",
                        HttpStatus.BAD_REQUEST,
                        errors
                )
        );
    }
}

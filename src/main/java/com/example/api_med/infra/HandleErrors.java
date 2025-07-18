package com.example.api_med.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404()
    {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception)
    {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationErrors::new).toList());
    }

    private record DataValidationErrors(String field, String message)
    {
        public DataValidationErrors(FieldError fieldError)
        {
            this(fieldError.getField(),fieldError.getDefaultMessage());
        }
    }
}

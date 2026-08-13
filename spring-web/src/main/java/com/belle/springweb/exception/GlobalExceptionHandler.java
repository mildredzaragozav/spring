package com.belle.springweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> notFoundExceptionHandler(HttpClientErrorException.NotFound exception) {
        //ErrorResponse error = ErrorResponse.create(exception, exception.getStatusCode(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("External resource not found: " + exception.getStatusText());
    }

}

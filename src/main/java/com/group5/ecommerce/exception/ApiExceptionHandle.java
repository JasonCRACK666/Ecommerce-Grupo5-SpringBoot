package com.group5.ecommerce.exception;

import com.group5.ecommerce.exception.notFound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandle {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<NotFoundException> handleNotFoundException(RuntimeException e) {
        NotFoundException notFoundException = NotFoundException.builder()
                .code(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(notFoundException, HttpStatus.NOT_FOUND);
    }
}

package com.group5.ecommerce.exception;

import com.group5.ecommerce.response.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandle {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIsNotOwnerException.class)
    public ResponseEntity<ErrorResponse> handleUserIsNotOwnerException(UserIsNotOwnerException e) {
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotUploadedException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotUploadedException(
            ResourceNotUploadedException e
    ) {
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAccountNotActivatedException.class)
    public ResponseEntity<ErrorResponse> handleUserAccountNotActivatedException(
            UserAccountNotActivatedException e
    ) {
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAccountIsActivatedException.class)
    public ResponseEntity<ErrorResponse> handleUserAccountIsActivatedException(
            UserAccountIsActivatedException e
    ) {
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailNotSentException.class)
    public ResponseEntity<ErrorResponse> handleMailNotSentException(
            MailNotSentException e
    ) {
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

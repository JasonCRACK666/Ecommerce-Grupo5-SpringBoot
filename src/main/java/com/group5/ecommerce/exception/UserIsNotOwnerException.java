package com.group5.ecommerce.exception;

public class UserIsNotOwnerException extends Exception {
    public UserIsNotOwnerException(String message) {
        super(message);
    }
}

package com.group5.ecommerce.exception;

public class UserAccountIsActivatedException extends Exception {
    public UserAccountIsActivatedException() {
        super("La cuenta ya ha sido activada");
    }
}

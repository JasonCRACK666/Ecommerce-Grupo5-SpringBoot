package com.group5.ecommerce.exception;

public class UserAccountNotActivatedException extends Exception {
    public UserAccountNotActivatedException() {
        super("Esta cuenta no ha sido activada");
    }
}

package com.group5.ecommerce.exception;

import java.io.IOException;

public class ResourceNotUploadedException extends IOException {
    public ResourceNotUploadedException(String message) {
        super(message);
    }
}

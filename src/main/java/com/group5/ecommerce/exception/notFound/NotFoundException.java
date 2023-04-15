package com.group5.ecommerce.exception.notFound;

import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class NotFoundException {
    private final String message;
    private final int status;
    private final HttpStatus code;
}

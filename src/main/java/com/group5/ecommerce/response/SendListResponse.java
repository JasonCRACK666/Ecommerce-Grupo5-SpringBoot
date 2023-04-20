package com.group5.ecommerce.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class SendListResponse<T> {
    private final List<T> data;
}

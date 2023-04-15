package com.group5.ecommerce.service.base;

import com.group5.ecommerce.response.PongResponse;

public interface BaseService {
    PongResponse getPong();
    PongResponse sendMessage(String message);
}

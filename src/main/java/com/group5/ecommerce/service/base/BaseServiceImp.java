package com.group5.ecommerce.service.base;

import com.group5.ecommerce.response.PongResponse;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImp implements BaseService {
    @Override
    public PongResponse getPong() {
        return new PongResponse("Pong");
    }

    @Override
    public PongResponse sendMessage(String message) {
        return new PongResponse(message);
    }
}

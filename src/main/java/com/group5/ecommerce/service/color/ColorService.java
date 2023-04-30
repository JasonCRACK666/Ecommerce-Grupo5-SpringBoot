package com.group5.ecommerce.service.color;

import com.group5.ecommerce.dto.color.CreateColorDto;
import com.group5.ecommerce.entity.Color;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;

public interface ColorService {
    Color save(CreateColorDto colorData);
    MessageResponse deleteColor(Long Id);
    SendListResponse<Color> getAllColor();
}

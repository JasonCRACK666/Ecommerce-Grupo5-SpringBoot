package com.group5.ecommerce.service.colors;

import com.group5.ecommerce.dto.color.CreateColorDto;
import com.group5.ecommerce.dto.color.UpdateColorDto;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.color.ColorResponse;
import com.group5.ecommerce.response.color.DetailColorResponse;

public interface ColorService {
    ColorResponse save(CreateColorDto colorData);
    MessageResponse deleteColor(Long Id);
    SendListResponse<ColorResponse> getAllColor();
    DetailColorResponse updateColor(Long id, UpdateColorDto colorData);
}

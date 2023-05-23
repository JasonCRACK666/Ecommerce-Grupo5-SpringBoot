package com.group5.ecommerce.service.color;

import com.group5.ecommerce.dto.color.CreateColorDto;
import com.group5.ecommerce.dto.color.UpdateColorDto;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.color.ColorResponse;
import com.group5.ecommerce.response.color.DetailColorResponse;

public interface ColorService {
    SendListResponse<ColorResponse> getAllColor();
    ColorResponse getColorDetail(Long colorId);
    ColorResponse save(CreateColorDto colorData);
    MessageResponse deleteColor(Long Id);
    DetailColorResponse updateColor(Long id, UpdateColorDto colorData);
}

package com.group5.ecommerce.service.color;

import com.group5.ecommerce.dto.color.CreateColorDto;
import com.group5.ecommerce.entity.Color;
import com.group5.ecommerce.exception.NotFoundException;
import com.group5.ecommerce.repository.ColorRepository;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Color save(CreateColorDto colorData) {
        Color color = Color.builder()
                .name(colorData.getName())
                .hex(colorData.getHex())
                .build();

        return colorRepository.save(color);
    }

    @Override
    public MessageResponse deleteColor(Long colorId) {
        Color color = colorRepository
                .findById(colorId)
                .orElseThrow(
                        () -> new NotFoundException("El color no existe")
                );

        colorRepository.delete(color);

        return new MessageResponse("El color ha sido elimiando");
    }

    @Override
    public SendListResponse<Color> getAllColor() {
        return new SendListResponse<>(colorRepository.findAll());
    }
}

package com.group5.ecommerce.service.color;

import com.group5.ecommerce.dto.color.CreateColorDto;
import com.group5.ecommerce.dto.color.UpdateColorDto;
import com.group5.ecommerce.entity.Color;
import com.group5.ecommerce.exception.NotFoundException;
import com.group5.ecommerce.repository.ColorRepository;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.color.ColorMapper;
import com.group5.ecommerce.response.color.ColorResponse;
import com.group5.ecommerce.response.color.DetailColorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public ColorResponse save(CreateColorDto colorData) {
        Color color = Color.builder()
                .name(colorData.getName())
                .hex(colorData.getHex())
                .build();

        Color savedColor = colorRepository.save(color);

        return ColorMapper.INSTANCE.toResponse(savedColor);
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
    public SendListResponse<ColorResponse> getAllColor() {
        List<Color> colors = this.colorRepository.findAll();
        List<ColorResponse> colorResponses = ColorMapper.INSTANCE.toListResponse(colors);
        return new SendListResponse<>(colorResponses);
    }

    @Override
    public DetailColorResponse updateColor(Long id, UpdateColorDto colorData) {
        Color color = this.colorRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundReqException("El color no existe")
                );

        color.setName(colorData.getName());
        color.setHex(colorData.getHex());

        var savedColor = this.colorRepository.save(color);

        return ColorMapper.INSTANCE.toDetailResponse(savedColor);
    }

}

package com.group5.ecommerce.response.color;

import com.group5.ecommerce.entity.Color;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

    ColorResponse toResponse(Color color);

    List<ColorResponse> toListResponse(List<Color> colors);

    DetailColorResponse toDetailResponse(Color color);

}

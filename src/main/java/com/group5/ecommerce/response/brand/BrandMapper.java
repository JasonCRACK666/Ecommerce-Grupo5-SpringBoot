package com.group5.ecommerce.response.brand;

import com.group5.ecommerce.entity.Brand;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandResponse toResponse(Brand brand);

    DetailBrandResponse toDetailResponse(Brand brand);

}

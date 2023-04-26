package com.group5.ecommerce.response.product;

import com.group5.ecommerce.entity.Product;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    DetailProductResponse toResponse(Product product);

    @InheritInverseConfiguration
    Product toEntity(DetailProductResponse productResponse);

}

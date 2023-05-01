package com.group5.ecommerce.response.product;

import com.group5.ecommerce.entity.Product;

import com.group5.ecommerce.response.brand.BrandResponse;
import com.group5.ecommerce.response.category.CategoryResponse;
import com.group5.ecommerce.response.color.ColorResponse;
import com.group5.ecommerce.response.image.ImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    DetailProductResponse toResponse(Product product);

    default List<ProductResponse> toListResponse(List<Product> products) {
        return products.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getTitle(),
                        product.getOriginalPrice(),
                        product.getDiscountRate(),
                        product.getQuantity(),
                        product.getPublicationDate(),
                        new CategoryResponse(
                                product.getCategory().getId(),
                                product.getCategory().getName()
                        ),
                        new BrandResponse(
                                product.getBrand().getId(),
                                product.getBrand().getName(),
                                product.getBrand().getLogo()
                        ),
                        new ImageResponse(
                                product.getImages().get(0).getId(),
                                product.getImages().get(0).getImageUrl()
                        ),
                        product.getColors().stream()
                                .map(color -> new ColorResponse(
                                        color.getId(),
                                        color.getName(),
                                        color.getHex()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

}

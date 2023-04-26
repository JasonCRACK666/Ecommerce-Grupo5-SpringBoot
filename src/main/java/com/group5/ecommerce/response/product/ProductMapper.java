package com.group5.ecommerce.response.product;

import com.group5.ecommerce.entity.Image;
import com.group5.ecommerce.entity.Product;

import com.group5.ecommerce.response.image.ImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    DetailProductResponse toResponse(Product product);

    @Mapping(source = "images", target = "image", qualifiedByName = "imageToImageResponse")
    List<ProductResponse> toListResponse(List<Product> products);

    @Named("imageToImageResponse")
    default ImageResponse imageToImageResponse(List<Image> images) {
        return images.stream().findFirst()
                .map(image -> new ImageResponse(image.getId(), image.getImageUrl()))
                .orElse(null);
    }

}

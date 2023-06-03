package com.group5.ecommerce.response.product;

import com.group5.ecommerce.entity.Product;
import com.group5.ecommerce.response.brand.BrandResponse;
import com.group5.ecommerce.response.category.CategoryResponse;
import com.group5.ecommerce.response.color.ColorResponse;
import com.group5.ecommerce.response.image.ImageResponse;
import com.group5.ecommerce.utils.MathOperationsUtils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {MathOperationsUtils.class })
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(
            target = "publicationDate",
            expression = "java(product.getPublicationDate().toString())"
    )
    @Mapping(
            target = "finalPrice",
            expression = "java(MathOperationsUtils.getFinalPrice(product.getOriginalPrice(), product.getDiscountRate()))"
    )
    @Mapping(
            target = "averageScore",
            expression = "java(MathOperationsUtils.getAverageScore(product.getReviews()))"
    )
    @Mapping(
            target = "countReviews",
            expression = "java(product.getReviews() == null ? 0 : product.getReviews().size())"
    )
    DetailProductResponse toResponse(Product product);

    default List<ProductResponse> toListResponse(List<Product> products) {
        return products.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getTitle(),
                        product.getOriginalPrice(),
                        product.getDiscountRate(),
                        product.getQuantity(),
                        product.getPublicationDate().toString(),
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
                        MathOperationsUtils.getFinalPrice(
                                product.getOriginalPrice(),
                                product.getDiscountRate()
                        ),
                        MathOperationsUtils.getAverageScore(product.getReviews()),
                        product.getReviews() == null ? 0 : product.getReviews().size(),
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

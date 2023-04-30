package com.group5.ecommerce.response.image;

import com.group5.ecommerce.entity.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageResponse toResponse(Image image);

    List<ImageResponse> toListResponse(List<Image> images);

    default ImageResponse listToImageResponse(List<Image> images) {
        return images.stream().findFirst()
                .map(image -> new ImageResponse(image.getId(), image.getImageUrl()))
                .orElse(null);
    }
}

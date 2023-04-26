package com.group5.ecommerce.response.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    private Long id;
    private String imageUrl;
}

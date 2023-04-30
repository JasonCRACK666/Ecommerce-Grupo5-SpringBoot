package com.group5.ecommerce.response.product;

import com.group5.ecommerce.response.brand.BrandResponse;
import com.group5.ecommerce.response.category.CategoryResponse;
import com.group5.ecommerce.response.color.ColorResponse;
import com.group5.ecommerce.response.image.ImageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String title;
    private BigDecimal originalPrice;
    private Integer discountRate;
    private Integer quantity;
    private LocalDateTime publicationDate;
    private CategoryResponse category;
    private BrandResponse brand;
    private ImageResponse image;
    private List<ColorResponse> colors;
}

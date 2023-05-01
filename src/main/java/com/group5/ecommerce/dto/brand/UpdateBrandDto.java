package com.group5.ecommerce.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandDto {
    private String name;
    private MultipartFile logo;
    private MultipartFile banner;
}

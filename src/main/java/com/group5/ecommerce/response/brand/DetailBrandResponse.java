package com.group5.ecommerce.response.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailBrandResponse {
    private Long id;
    private String name;
    private String logo;
    private String banner;
}

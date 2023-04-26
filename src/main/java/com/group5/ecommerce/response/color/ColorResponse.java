package com.group5.ecommerce.response.color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorResponse {
    private Long id;
    private String name;
    private String hex;
}

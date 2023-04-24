package com.group5.ecommerce.service.brand;

import com.group5.ecommerce.dto.brand.CreateBrandDto;
import com.group5.ecommerce.entity.Brand;

import java.io.IOException;

public interface BrandService {
    Brand saveBrand(CreateBrandDto brandData) throws IOException;
}

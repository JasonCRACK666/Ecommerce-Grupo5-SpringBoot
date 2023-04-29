package com.group5.ecommerce.service.brand;

import com.group5.ecommerce.dto.brand.CreateBrandDto;
import com.group5.ecommerce.response.brand.BrandResponse;

public interface BrandService {
    BrandResponse saveBrand(CreateBrandDto brandData);
}

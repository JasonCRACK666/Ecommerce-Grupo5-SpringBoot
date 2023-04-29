package com.group5.ecommerce.service.brand;

import com.group5.ecommerce.dto.brand.CreateBrandDto;
import com.group5.ecommerce.dto.brand.UpdateBrandDto;
import com.group5.ecommerce.response.brand.DetailBrandResponse;

public interface BrandService {
    DetailBrandResponse saveBrand(CreateBrandDto brandData);
    DetailBrandResponse updateBrand(Long brandId, UpdateBrandDto brandData);
}

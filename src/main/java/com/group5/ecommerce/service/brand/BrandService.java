package com.group5.ecommerce.service.brand;

import com.group5.ecommerce.dto.brand.CreateBrandDto;
import com.group5.ecommerce.dto.brand.UpdateBrandDto;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.brand.BrandResponse;
import com.group5.ecommerce.response.brand.DetailBrandResponse;

public interface BrandService {
    SendListResponse<BrandResponse> getAllBrands();
    DetailBrandResponse getDetailBrandById(Long brandId);
    DetailBrandResponse saveBrand(CreateBrandDto brandData);
    MessageResponse followBrand(Long userId ,Long brandId);
    MessageResponse deleteBrand(Long brandId);
    DetailBrandResponse updateBrand(Long brandId, UpdateBrandDto brandData);
}

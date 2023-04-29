package com.group5.ecommerce.service.brand;

import com.group5.ecommerce.dto.brand.CreateBrandDto;
import com.group5.ecommerce.entity.Brand;
import com.group5.ecommerce.repository.BrandRepository;
import com.group5.ecommerce.response.brand.BrandMapper;
import com.group5.ecommerce.response.brand.BrandResponse;
import com.group5.ecommerce.utils.CloudinaryUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {

    private final CloudinaryUtils cloudinaryUtils;

    private final BrandRepository brandRepository;

    @Override
    public BrandResponse saveBrand(CreateBrandDto brandData) {
        String logoUrl, bannerUrl;

        try {
            logoUrl = this.cloudinaryUtils.uploadImage(brandData.getLogo());
            bannerUrl = this.cloudinaryUtils.uploadImage(brandData.getBanner());
        } catch (IOException exception) {
            throw new RuntimeException("El avatar o el banner no se ha podido subir");
        }

        var brand = Brand.builder()
                .name(brandData.getName())
                .logo(logoUrl)
                .banner(bannerUrl)
                .build();

        var savedBrand = this.brandRepository.save(brand);

        return BrandMapper.INSTANCE.toResponse(savedBrand);
    }
}

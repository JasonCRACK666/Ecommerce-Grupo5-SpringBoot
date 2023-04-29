package com.group5.ecommerce.service.brand;

import com.group5.ecommerce.dto.brand.CreateBrandDto;
import com.group5.ecommerce.dto.brand.UpdateBrandDto;
import com.group5.ecommerce.entity.Brand;
import com.group5.ecommerce.exception.notFound.NotFoundReqException;
import com.group5.ecommerce.repository.BrandRepository;
import com.group5.ecommerce.response.brand.BrandMapper;
import com.group5.ecommerce.response.brand.DetailBrandResponse;
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
    public DetailBrandResponse saveBrand(CreateBrandDto brandData) {
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

        return BrandMapper.INSTANCE.toDetailResponse(savedBrand);
    }

    @Override
    public DetailBrandResponse updateBrand(Long brandId, UpdateBrandDto brandData) {
        var brand = this.brandRepository
                .findById(brandId)
                .orElseThrow(
                        () -> new NotFoundReqException("La marca no existe")
                );

        if (brandData.getName() != null)
            brand.setName(brandData.getName());

        try {
            if (brandData.getLogo() != null)
                brand.setLogo(this.cloudinaryUtils.uploadImage(brandData.getLogo()));
        } catch (IOException e) {
            throw new RuntimeException("No se ha podido actualizar el logo");
        }

        try {
            if (brandData.getBanner() != null)
                brand.setBanner(this.cloudinaryUtils.uploadImage(brandData.getBanner()));
        } catch (IOException e) {
            throw new RuntimeException("No se ha podido actualizar el banner");
        }

        var savedBrand = this.brandRepository.save(brand);

        return BrandMapper.INSTANCE.toDetailResponse(savedBrand);
    }
}

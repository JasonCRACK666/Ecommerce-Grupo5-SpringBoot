package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.brand.CreateBrandDto;
import com.group5.ecommerce.dto.brand.FollowBrandDto;
import com.group5.ecommerce.dto.brand.UpdateBrandDto;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.brand.DetailBrandResponse;
import com.group5.ecommerce.service.brand.BrandServiceImp;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/brands")
public class BrandController {
    @Autowired
    private BrandServiceImp brandService;

    @PostMapping
    public ResponseEntity<DetailBrandResponse> createBrand(
            @Valid CreateBrandDto brandData
    ) {
        return new ResponseEntity<>(this.brandService.saveBrand(brandData), HttpStatus.OK);
    }

    @PostMapping(path = "follow")
    public ResponseEntity<MessageResponse> followBrand(
            @RequestAttribute("user") Long userId,
            @Valid @RequestBody FollowBrandDto brandData
    ) {
        return new ResponseEntity<>(this.brandService.followBrand(userId, brandData.getBrandId()), HttpStatus.OK);
    }

    @PatchMapping(path = "{brandId}")
    public ResponseEntity<DetailBrandResponse> updateBrand(
            @PathVariable("brandId") Long brandId,
            @Valid UpdateBrandDto brandData
    ) {
        return new ResponseEntity<>(this.brandService.updateBrand(brandId, brandData), HttpStatus.OK);
    }
}

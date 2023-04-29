package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.brand.CreateBrandDto;
import com.group5.ecommerce.response.brand.BrandResponse;
import com.group5.ecommerce.service.brand.BrandServiceImp;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/brands")
public class BrandController {
    @Autowired
    private BrandServiceImp brandService;

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(
            @Valid CreateBrandDto brandData
    ) {
        return new ResponseEntity<>(this.brandService.saveBrand(brandData), HttpStatus.OK);
    }
}

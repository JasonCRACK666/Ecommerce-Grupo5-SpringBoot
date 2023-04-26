package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.product.CreateProductDto;
import com.group5.ecommerce.entity.Product;
import com.group5.ecommerce.response.product.DetailProductResponse;
import com.group5.ecommerce.service.product.ProductServiceImp;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    @Autowired
    private ProductServiceImp productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @Valid CreateProductDto productData
    ) {
        return new ResponseEntity<>(this.productService.saveProduct(productData), HttpStatus.OK);
    }

    @GetMapping(path = "{productId}")
    public ResponseEntity<DetailProductResponse> detailProduct(
            @PathVariable("productId") Long productId
    ) {
        return new ResponseEntity<>(this.productService.detailProduct(productId), HttpStatus.OK);
    }
}

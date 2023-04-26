package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.product.CreateProductDto;
import com.group5.ecommerce.response.product.DetailProductResponse;
import com.group5.ecommerce.response.product.PaginatedProductsResponse;
import com.group5.ecommerce.service.product.ProductServiceImp;

import com.group5.ecommerce.utils.ApiConstants;
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

    @GetMapping
    public ResponseEntity<PaginatedProductsResponse> products(
            @RequestParam(
                    value = "page",
                    defaultValue = ApiConstants.DEFAULT_PAGE_NUMBER,
                    required = false
            )
            int page,
            @RequestParam(
                    value = "size",
                    defaultValue = ApiConstants.DEFAULT_PAGE_SIZE,
                    required = false
            )
            int size
    ) {
        return new ResponseEntity<>(this.productService.getAllProducts(page, size), HttpStatus.OK);
    }

    @GetMapping(path = "{productId}")
    public ResponseEntity<DetailProductResponse> detailProduct(
            @PathVariable("productId") Long productId
    ) {
        return new ResponseEntity<>(this.productService.detailProduct(productId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailProductResponse> createProduct(
            @Valid CreateProductDto productData
    ) {
        return new ResponseEntity<>(this.productService.saveProduct(productData), HttpStatus.OK);
    }

}

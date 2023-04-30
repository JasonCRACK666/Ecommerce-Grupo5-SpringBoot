package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.product.CreateProductDto;
import com.group5.ecommerce.dto.product.UpdateProductDto;
import com.group5.ecommerce.entity.enums.SearchOrder;
import com.group5.ecommerce.entity.enums.SortBy;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.product.DetailProductResponse;
import com.group5.ecommerce.response.product.PaginatedProductsResponse;
import com.group5.ecommerce.service.product.ProductService;
import com.group5.ecommerce.utils.ApiConstants;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

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

    @GetMapping("search")
    public ResponseEntity<PaginatedProductsResponse> searchProducts(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "sortBy", required = false, defaultValue = "title") SortBy sortBy,
            @RequestParam(value = "order", required = false, defaultValue = "desc") SearchOrder order,
            @RequestParam(value = "category", required = false) String categoryName,
            @RequestParam(value = "brand", required = false) String brandName,
            @RequestParam(value = "colors", required = false) List<String> colorNames,
            @RequestParam(value = "limitPrice", required = false) BigDecimal limitPrice,
            @RequestParam(value = "inOffer", defaultValue = "false") boolean inOffer,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = ApiConstants.DEFAULT_PAGE_NUMBER
            )
            int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = ApiConstants.DEFAULT_PAGE_SIZE
            )
            int size
    ) {
        return new ResponseEntity<>(
                this.productService.searchProducts(
                        query,
                        sortBy,
                        order,
                        categoryName,
                        brandName,
                        colorNames,
                        limitPrice,
                        inOffer,
                        page,
                        size
                ),
                HttpStatus.OK
        );
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

    @PatchMapping(path = "{productId}")
    public ResponseEntity<DetailProductResponse> updateProduct(
            @PathVariable("productId") Long productId,
            @Valid @RequestBody UpdateProductDto productData
    ) {
        return new ResponseEntity<>(
                this.productService.updateProduct(productId, productData),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<MessageResponse> deleteProduct(
            @PathVariable("productId") Long productId
    ) {
        return new ResponseEntity<>(this.productService.deleteProduct(productId), HttpStatus.OK);
    }

}

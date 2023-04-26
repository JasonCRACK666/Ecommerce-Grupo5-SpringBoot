package com.group5.ecommerce.service.product;

import com.group5.ecommerce.dto.product.CreateProductDto;
import com.group5.ecommerce.response.product.DetailProductResponse;
import com.group5.ecommerce.response.product.PaginatedProductsResponse;

public interface ProductService {
    PaginatedProductsResponse getAllProducts(int page, int size);
    DetailProductResponse detailProduct(Long productId);
    DetailProductResponse saveProduct(CreateProductDto productData);
}

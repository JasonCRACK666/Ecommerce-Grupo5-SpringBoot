package com.group5.ecommerce.service.product;

import com.group5.ecommerce.dto.product.CreateProductDto;
import com.group5.ecommerce.response.product.DetailProductResponse;

public interface ProductService {
    DetailProductResponse saveProduct(CreateProductDto productData);
    DetailProductResponse detailProduct(Long productId);
}

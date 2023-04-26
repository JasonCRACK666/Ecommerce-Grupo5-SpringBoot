package com.group5.ecommerce.service.product;

import com.group5.ecommerce.dto.product.CreateProductDto;
import com.group5.ecommerce.entity.Product;
import com.group5.ecommerce.response.product.DetailProductResponse;

public interface ProductService {
    Product saveProduct(CreateProductDto productData);
    DetailProductResponse detailProduct(Long productId);
}

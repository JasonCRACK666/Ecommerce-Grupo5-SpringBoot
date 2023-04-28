package com.group5.ecommerce.service.product;

import com.group5.ecommerce.dto.product.CreateProductDto;
import com.group5.ecommerce.entity.enums.SearchOrder;
import com.group5.ecommerce.entity.enums.SortBy;
import com.group5.ecommerce.response.product.DetailProductResponse;
import com.group5.ecommerce.response.product.PaginatedProductsResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    PaginatedProductsResponse getAllProducts(int page, int size);
    PaginatedProductsResponse searchProducts(
            String query,
            SortBy sortBy,
            SearchOrder order,
            String categoryName,
            String brandName,
            List<String> colorNames,
            BigDecimal limitPrice,
            boolean inOffer,
            int page,
            int size
    );
    DetailProductResponse detailProduct(Long productId);
    DetailProductResponse saveProduct(CreateProductDto productData);
}

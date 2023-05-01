package com.group5.ecommerce.repository.product;

import com.group5.ecommerce.entity.Brand;
import com.group5.ecommerce.entity.Category;
import com.group5.ecommerce.entity.Product;
import com.group5.ecommerce.entity.enums.SearchOrder;
import com.group5.ecommerce.entity.enums.SortBy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CustomProductRepository {
    Page<Product> searchBy(
            String query,
            SortBy sortBy,
            SearchOrder order,
            Category category,
            Brand brand,
            List<String> colors,
            BigDecimal limitPrice,
            boolean inOffer,
            Pageable pageable
    );
}

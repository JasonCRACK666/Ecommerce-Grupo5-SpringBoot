package com.group5.ecommerce.service.category;

import com.group5.ecommerce.dto.category.UpdateCategoryDto;
import com.group5.ecommerce.entity.Category;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.category.DetailCategoryResponse;

public interface CategoryService {
    SendListResponse<Category> getAllCategory();
    DetailCategoryResponse updateCategory(Long id, UpdateCategoryDto categoryData);
}

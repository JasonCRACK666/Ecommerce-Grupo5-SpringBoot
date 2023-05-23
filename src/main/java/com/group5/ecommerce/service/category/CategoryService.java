package com.group5.ecommerce.service.category;

import com.group5.ecommerce.dto.category.CreateCategoryDto;
import com.group5.ecommerce.dto.category.UpdateCategoryDto;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.category.CategoryResponse;
import com.group5.ecommerce.response.category.DetailCategoryResponse;

public interface CategoryService {
    SendListResponse<CategoryResponse> getAllCategory();
        CategoryResponse getCategoryById(Long categoryId);
        CategoryResponse createCategory(CreateCategoryDto categoryData);
    MessageResponse deleteCategory(Long id);
    DetailCategoryResponse updateCategory(Long id, UpdateCategoryDto categoryData);
}

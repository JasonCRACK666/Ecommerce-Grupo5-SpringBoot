package com.group5.ecommerce.service.category;

import com.group5.ecommerce.dto.category.UpdateCategoryDto;
import com.group5.ecommerce.entity.Category;
import com.group5.ecommerce.exception.NotFoundException;
import com.group5.ecommerce.repository.CategoryRepository;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.category.CategoryMapper;
import com.group5.ecommerce.response.category.DetailCategoryResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public SendListResponse<Category> getAllCategory() {
        return new SendListResponse<>(categoryRepository.findAll());
    }

    @Override
    public DetailCategoryResponse updateCategory(Long categoryId, UpdateCategoryDto categoryData) {
        Category category = this.categoryRepository
                .findById(categoryId)
                .orElseThrow(
                        () -> new NotFoundException("La categoría no existe")
                );

        category.setName(categoryData.getName());

        var savedCategory = this.categoryRepository.save(category);

        return CategoryMapper.INSTANCE.toDetailResponse(savedCategory);
    }
}

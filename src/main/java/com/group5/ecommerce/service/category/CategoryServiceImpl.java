package com.group5.ecommerce.service.category;

import com.group5.ecommerce.dto.category.CreateCategoryDto;
import com.group5.ecommerce.dto.category.UpdateCategoryDto;
import com.group5.ecommerce.entity.Category;
import com.group5.ecommerce.exception.NotFoundException;
import com.group5.ecommerce.repository.CategoryRepository;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.category.CategoryMapper;
import com.group5.ecommerce.response.category.CategoryResponse;
import com.group5.ecommerce.response.category.DetailCategoryResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public SendListResponse<CategoryResponse> getAllCategory() {
        var categories = this.categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = CategoryMapper.INSTANCE.toListResponse(categories);
        return new SendListResponse<>(categoryResponses);
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = this.categoryRepository
                .findById(categoryId)
                .orElseThrow(
                        () -> new NotFoundException("La categoria no existe")
                );

        return CategoryMapper.INSTANCE.toResponse(category);
    }

    @Override
    public CategoryResponse createCategory(CreateCategoryDto categoryData) {
        var category = Category.builder()
                .name(categoryData.getName())
                .build();

        var savedCategory = this.categoryRepository.save(category);

        return CategoryMapper.INSTANCE.toResponse(savedCategory);
    }

    @Override
    public MessageResponse deleteCategory(Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(
                        () -> new NotFoundException("La categoria no existe")
                );
            categoryRepository.delete(category);

            return  new MessageResponse("La categoria a sido elimado");
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

package com.group5.ecommerce.response.category;

import com.group5.ecommerce.entity.Category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toListResponse(List<Category> categories);

    DetailCategoryResponse toDetailResponse(Category category);

}

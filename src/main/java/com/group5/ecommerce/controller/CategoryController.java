package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.category.CreateCategoryDto;
import com.group5.ecommerce.dto.category.UpdateCategoryDto;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.category.CategoryResponse;
import com.group5.ecommerce.response.category.DetailCategoryResponse;
import com.group5.ecommerce.service.category.CategoryServiceImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<SendListResponse<CategoryResponse>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getAllCategory(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CreateCategoryDto categoryData
    ) {
        return new ResponseEntity<>(this.categoryService.createCategory(categoryData), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<MessageResponse> deleteCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(this.categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping(path = "{categoryId}")
    public ResponseEntity<DetailCategoryResponse> updateCategory(
            @PathVariable("categoryId") Long categoryId,
            @Valid @RequestBody UpdateCategoryDto categoryData
    ) {
        return new ResponseEntity<>(this.categoryService.updateCategory(categoryId, categoryData), HttpStatus.OK);
    }

}

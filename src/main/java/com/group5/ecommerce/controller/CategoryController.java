package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.category.UpdateCategoryDto;
import com.group5.ecommerce.entity.Category;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.category.DetailCategoryResponse;
import com.group5.ecommerce.service.category.CategoryServiceImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<SendListResponse<Category>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getAllCategory(), HttpStatus.OK);
    }

    @PutMapping(path = "{categoryId}")
    public ResponseEntity<DetailCategoryResponse> updateCategory(
            @PathVariable("categoryId") Long categoryId,
            @Valid @RequestBody UpdateCategoryDto categoryData
    ) {
        return new ResponseEntity<>(this.categoryService.updateCategory(categoryId, categoryData), HttpStatus.OK);
    }

}

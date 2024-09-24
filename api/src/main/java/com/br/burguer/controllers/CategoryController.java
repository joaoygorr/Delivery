package com.br.burguer.controllers;

import com.br.burguer.record.category.CategoryDTO;
import com.br.burguer.record.category.CategoryNewDTO;
import com.br.burguer.services.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Category related endpoint")
public class CategoryController {

    private final CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "List all categories", description = "Returns a list of categories")
    public ResponseEntity<Page<CategoryDTO>> getAll(Pageable pageable) {
        Page<CategoryDTO> getCategoryAll = this.categoryService.getAllCategories(pageable);
        return ResponseEntity.ok(getCategoryAll);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a category", description = "Returns a category based on id")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        CategoryDTO getCategory = this.categoryService.getCategoryById(id);
        return ResponseEntity.ok(getCategory);
    }

    @PostMapping
    @Operation(summary = "Create category", description = "Creating a category")
    public ResponseEntity<CategoryNewDTO> postCategory(@RequestBody @Valid CategoryNewDTO categoryNewDTO) {
        CategoryNewDTO createCategory  = this.categoryService.createCategory(categoryNewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCategory);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category", description = "Delete a category by id")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        this.categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}

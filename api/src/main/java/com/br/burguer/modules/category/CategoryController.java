package com.br.burguer.modules.category;

import com.br.burguer.modules.category.dto.CategoryDTO;
import com.br.burguer.services.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Page<CategoryDTO> categoryDTOS = this.categoryService.getAllCategories(pageable);
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a category", description = "Returns a category based on id")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        CategoryDTO categoryDTO = this.categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }
}

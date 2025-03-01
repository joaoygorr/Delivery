package br.com.delivery.controllers;

import br.com.delivery.modules.category.mapper.CategoryMapper;
import br.com.delivery.records.category.CategoryRecord;
import br.com.delivery.services.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Category", description = "Endpoint related to category")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @PostMapping("/create")
    @Operation(summary = "Record a new category",
            description = "Create a new category in the system based on the data provided in the request. Returns the details of the registered category.")
    public ResponseEntity<CategoryRecord> post(@RequestBody @Valid CategoryRecord categoryRecord) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.categoryMapper.toDto(this.categoryService.createCategory(categoryRecord)));
    }
}
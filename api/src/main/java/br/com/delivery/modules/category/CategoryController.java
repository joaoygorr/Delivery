package br.com.delivery.modules.category;

import br.com.delivery.modules.category.mapper.CategoryMapper;
import br.com.delivery.modules.category.records.CategoryRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .body(this.categoryMapper.toDto(
                        this.categoryService.createCategory(
                                this.categoryMapper.toEntity(categoryRecord))));
    }

    @GetMapping("/all")
    @Operation(summary = "Retrieve all categories",
            description = "Fetch a paginated list of all categories available in the system.")
    public ResponseEntity<Page<CategoryRecord>> getAll(Pageable pageable) {
        Page<Category> categoriesPage = this.categoryService.getCategories(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(categoriesPage.map(this.categoryMapper::toDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category from the system by its ID")
    public ResponseEntity<Void> getAll(@PathVariable Long id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "Update a category from the system by its id")
    public ResponseEntity<CategoryRecord> editCategory(@PathVariable Long id, @RequestBody CategoryRecord categoryRecord) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.categoryMapper.toDto(
                        this.categoryService.updateCategory(id, categoryRecord)));
    }
}
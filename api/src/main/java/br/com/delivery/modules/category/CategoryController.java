package br.com.delivery.modules.category;

import br.com.delivery.modules.category.dtos.CategoryDTO;
import br.com.delivery.modules.category.mapper.CategoryMapper;
import br.com.delivery.modules.category.specifications.CategorySpec;
import br.com.delivery.modules.crud.CrudController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Category", description = "Endpoint related to category")
public class CategoryController extends CrudController<Category, CategoryRepository, CategorySpec, CategoryDTO, CategoryMapper> {

    private final CategoryService categoryService;

    @DeleteMapping("/{id}")
    @Override
    @Operation(summary = "Exclusão de categoria por ID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID.")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> show(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findByCategory(id));
    }
}
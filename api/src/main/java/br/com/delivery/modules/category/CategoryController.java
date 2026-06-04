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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Category", description = "Endpoint related to category")
public class CategoryController extends CrudController<Category, CategoryRepository, CategorySpec, CategoryDTO, CategoryMapper> {

    private final CategoryService categoryService;

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de entidade por ID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id) {
        categoryService.deleteCategory(id);
    }
}
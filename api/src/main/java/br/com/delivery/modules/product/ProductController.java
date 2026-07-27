package br.com.delivery.modules.product;

import br.com.delivery.modules.crud.CrudController;
import br.com.delivery.modules.product.dtos.ProductDTO;
import br.com.delivery.modules.product.mapper.ProductMapper;
import br.com.delivery.modules.product.specifications.ProductSpec;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Product", description = "Endpoint related to product")
public class ProductController extends CrudController<Product, ProductRepository, ProductSpec, ProductDTO, ProductMapper> {

    private final ProductService productService;

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de produto por ID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@ModelAttribute @Valid ProductDTO dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }
}

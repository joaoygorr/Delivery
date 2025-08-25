package br.com.delivery.modules.product;

import br.com.delivery.modules.product.mapper.ProductMapper;
import br.com.delivery.modules.product.records.ProductRecord;
import br.com.delivery.modules.product.records.ProductResponseRecord;
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
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @PostMapping("/create")
    @Operation(summary = "Record a new product",
            description = "Create a new product in the system based on the data provided in the request. Returns the details of the registered product.")
    public ResponseEntity<ProductResponseRecord> post(@ModelAttribute @Valid ProductRecord productRecord) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productMapper.toDto(this.productService.createProduct(productMapper.toEntity(productRecord))));
    }
}

package com.br.burguer.controllers;

import com.br.burguer.modules.product.dto.ProductDTO;
import com.br.burguer.services.product.ProductService;
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
@RequestMapping("/product")
@Tag(name = "Product", description = "Product related endpoint")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "List all products", description = "Returns a list of products")
    public ResponseEntity<Page<ProductDTO>> getAll(Pageable pageable) {
        Page<ProductDTO> productDTOS = this.productService.getAllProducts(pageable);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a product", description = "Returns a product based on id")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        ProductDTO productDTO = this.productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }
}

package com.br.burguer.record.product;

import com.br.burguer.modules.Category;
import com.br.burguer.modules.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDTO(Long idProduct, @NotNull Category category, @NotEmpty String name, String description,
                         BigDecimal price, Boolean complementary, @NotEmpty String urlImage) {

    public static Product toEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.idProduct,
                productDTO.category,
                productDTO.name,
                productDTO.description,
                productDTO.price,
                productDTO.complementary,
                productDTO.urlImage
        );
    }

    public static ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getIdProduct(),
                product.getCategory(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getComplementary(),
                product.getUrlImage()
        );
    }
}

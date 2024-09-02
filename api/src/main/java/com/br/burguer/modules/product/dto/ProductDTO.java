package com.br.burguer.modules.product.dto;

import com.br.burguer.modules.category.Category;
import com.br.burguer.modules.product.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ProductDTO {

    private Long idProduct;

    @NotNull
    private Category category;

    @NotEmpty
    private String name;

    private String description;

    private BigDecimal price;

    private Boolean complementary;

    @NotEmpty
    private String urlImage;

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

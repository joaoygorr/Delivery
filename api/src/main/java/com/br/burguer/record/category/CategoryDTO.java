package com.br.burguer.record.category;

import com.br.burguer.modules.Category;
import com.br.burguer.modules.Product;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoryDTO(Long idCategory,
                          @NotBlank(message = "Nome não pode vazio") String name,
                          List<Long> idProducts){

    public static Category toEntity(CategoryDTO categoryDto, List<Product> products) {
        return new Category(
                categoryDto.idCategory,
                categoryDto.name,
                products
        );
    }

    public static CategoryDTO toDto(Category category) {
        List<Long> productsIds = category.getProducts().stream().map(Product::getIdProduct).toList();

        return new CategoryDTO(
                category.getIdCategory(),
                category.getName(),
                productsIds
        );
    }
}

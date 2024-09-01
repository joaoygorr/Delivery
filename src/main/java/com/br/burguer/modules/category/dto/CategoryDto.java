package com.br.burguer.modules.category.dto;

import com.br.burguer.modules.category.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryDto {

    private Long idCategory;

    @NotNull
    private String label;

    public static Category toEntity(CategoryDto categoryDto) {
        return new Category(
                categoryDto.idCategory,
                categoryDto.label
        );
    }

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(
                category.getIdCategory(),
                category.getLabel()
        );
    }
}

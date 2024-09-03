package com.br.burguer.modules.category.dto;

import com.br.burguer.modules.category.Category;
import jakarta.validation.constraints.NotEmpty;

public record CategoryDTO(Long idCategory, @NotEmpty String label){

    public static Category toEntity(CategoryDTO categoryDto) {
        return new Category(
                categoryDto.idCategory,
                categoryDto.label
        );
    }

    public static CategoryDTO toDto(Category category) {
        return new CategoryDTO(
                category.getIdCategory(),
                category.getLabel()
        );
    }
}

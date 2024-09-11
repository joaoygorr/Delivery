package com.br.burguer.record.category;

import com.br.burguer.modules.Category;
import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(Long idCategory,
                          @NotBlank(message = "Label cannot be empty") String label){

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

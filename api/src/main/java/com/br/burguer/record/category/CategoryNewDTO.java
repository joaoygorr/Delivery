package com.br.burguer.record.category;

import com.br.burguer.modules.Category;
import jakarta.validation.constraints.NotBlank;

public record CategoryNewDTO(@NotBlank(message = "Nome não pode estar vazio") String name) {

    public static Category toEntity(CategoryNewDTO categoryNewDTO) {
        return new Category(
                null,
                categoryNewDTO.name,
                null
        );
    }

    public static CategoryNewDTO toDto(Category category) {
        return new CategoryNewDTO(
                category.getName()
        );
    }
}

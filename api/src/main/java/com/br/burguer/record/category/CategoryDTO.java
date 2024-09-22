package com.br.burguer.record.category;

import com.br.burguer.modules.Category;
import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(Long idCategory,
                          @NotBlank(message = "Nome não pode vazio") String nome){

    public static Category toEntity(CategoryDTO categoryDto) {
        return new Category(
                categoryDto.idCategory,
                categoryDto.nome
        );
    }

    public static CategoryDTO toDto(Category category) {
        return new CategoryDTO(
                category.getIdCategory(),
                category.getNome()
        );
    }
}

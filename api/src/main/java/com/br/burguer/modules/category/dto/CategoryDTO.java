package com.br.burguer.modules.category.dto;

import com.br.burguer.modules.category.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {

    private Long idCategory;

    @NotEmpty
    private String label;

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

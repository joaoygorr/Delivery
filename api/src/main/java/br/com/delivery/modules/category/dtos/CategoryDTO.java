package br.com.delivery.modules.category.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Nome não pode estar vazio")
    private String name;
}

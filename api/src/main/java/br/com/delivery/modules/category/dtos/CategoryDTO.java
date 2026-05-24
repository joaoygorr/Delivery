package br.com.delivery.modules.category.dtos;

import br.com.delivery.modules.crud.DtoBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryDTO extends DtoBase {

    @NotBlank(message = "Nome não pode estar vazio")
    private String name;
}

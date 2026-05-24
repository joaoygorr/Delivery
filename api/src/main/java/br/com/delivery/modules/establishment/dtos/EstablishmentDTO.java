package br.com.delivery.modules.establishment.dtos;

import br.com.delivery.modules.crud.DtoBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EstablishmentDTO extends DtoBase {

    @NotBlank(message = "Nome não pode estar vazio")
    private String name;

    @NotBlank(message = "Email não pode estar vazio")
    private String email;

    @NotBlank(message = "Telefone não pode estar vazio")
    private String phone;
}

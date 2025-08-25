package br.com.delivery.modules.establishment.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EstablishmentDTO {

    @NotBlank(message = "Nome não pode estar vazio")
    private String name;

    @NotBlank(message = "Email não pode estar vazio")
    private String email;

    @NotBlank(message = "Telefone não pode estar vazio")
    private String phone;
}

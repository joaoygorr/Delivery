package br.com.delivery.modules.user.dtos;

import br.com.delivery.modules.crud.DtoBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends DtoBase {

    @NotBlank(message = "UserName não pode estar em branco.")
    private String userName;

    @Email
    private String email;

    @NotBlank(message = "Senha não pode estar em branco.")
    private String password;
}

package br.com.delivery.modules.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "UserName não pode estar em branco.")
    private String userName;

    @Email
    private String email;

    @NotBlank(message = "Senha não pode estar em branco.")
    private String password;
}

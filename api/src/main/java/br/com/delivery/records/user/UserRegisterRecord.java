package br.com.delivery.records.user;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterRecord(@NotBlank(message = "Nome não pode estar em branco") String userName,
                                 @NotBlank(message = "E-mail não pode estar em branco") String email,
                                 @NotBlank(message = "Senha não pode estar em branco") String password) {
}

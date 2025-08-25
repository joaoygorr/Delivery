package br.com.delivery.modules.user.records;

import jakarta.validation.constraints.NotBlank;

public record UserRecord(Long id,
                         @NotBlank(message = "Nome não pode estar em branco") String userName,
                         @NotBlank(message = "E-mail não pode estar em branco") String email,
                         @NotBlank(message = "Senha não pode estar em branco") String password) {
}

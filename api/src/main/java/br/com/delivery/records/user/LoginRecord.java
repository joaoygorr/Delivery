package br.com.delivery.records.user;

import jakarta.validation.constraints.NotBlank;

public record LoginRecord(@NotBlank(message = "E-mail não pode estar em branco") String email,
                          @NotBlank(message = "Senha não pode estar em branco") String password) {
}

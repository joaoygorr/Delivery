package br.com.delivery.records.user;

import br.com.delivery.modules.user.User;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRecord(@NotBlank(message = "E-mail não pode estar em branco") String email,
                              @NotBlank(message = "Senha não pode estar em branco") String password){

    public static User toEntity(UserLoginRecord loginRecord) {
        return new User(loginRecord.email(), loginRecord.password());
    }
}

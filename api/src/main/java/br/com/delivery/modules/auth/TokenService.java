package br.com.delivery.modules.auth;

import br.com.delivery.modules.user.User;

public interface TokenService {

    String generateToken(User user);

    String validateToken(String token);
}

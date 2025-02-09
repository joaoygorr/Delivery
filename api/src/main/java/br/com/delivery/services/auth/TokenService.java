package br.com.delivery.services.auth;

import br.com.delivery.modules.user.User;

public interface TokenService {

    String generateToken(User user);

    String validateToken(String token);
}

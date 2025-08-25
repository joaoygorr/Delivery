package br.com.delivery.modules.user;

import br.com.delivery.configuration.exceptions.Exception401;
import br.com.delivery.configuration.exceptions.Exception404;
import br.com.delivery.modules.user.records.ResponseRecord;
import br.com.delivery.modules.auth.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseRecord login(User entity) {
        User user = this.userRepository.findByEmail(entity.getEmail())
                .orElseThrow(() -> new Exception404("Usuário não encontrado"));

        if (passwordEncoder.matches(entity.getPassword(), user.getPassword())) {
            return new ResponseRecord(user.getUsername(), this.tokenService.generateToken(user));
        }
        throw new Exception401("Credenciais inválidas");
    }

    @Override
    public ResponseRecord register(User entity) {
        Optional<User> user = this.userRepository.findByEmail(entity.getEmail());

        if (user.isEmpty()) {
            User newUser = new User(entity.getUsername(), entity.getEmail(), passwordEncoder.encode(entity.getPassword()));
            this.userRepository.save(newUser);

            return new ResponseRecord(newUser.getUsername(), this.tokenService.generateToken(newUser));
        }

        throw new Exception401("E-mail já cadastrado");
    }
}

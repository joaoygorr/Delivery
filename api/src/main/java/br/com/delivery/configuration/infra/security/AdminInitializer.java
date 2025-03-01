package br.com.delivery.configuration.infra.security;

import br.com.delivery.modules.user.User;
import br.com.delivery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AdminInitializer.class);

    @Override
    @Transactional
    public void run(String... args) {
        if (this.userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            User admin = new User("admin",
                    "admin@gmail.com",
                    passwordEncoder.encode("admin123"));

            this.userRepository.save(admin);
            logger.info("✅ Usuário Admin criado com sucesso!");
        } else {
            logger.warn("⚠️ Usuário Admin já existe.");
        }
    }
}

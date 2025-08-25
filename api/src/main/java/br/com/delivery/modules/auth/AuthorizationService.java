package br.com.delivery.modules.auth;

import br.com.delivery.modules.user.User;
import br.com.delivery.modules.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(("Usuário não encontrado")));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), new ArrayList<>());
    }
}

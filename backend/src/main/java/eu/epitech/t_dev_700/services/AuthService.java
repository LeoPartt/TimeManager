package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.models.AuthModels;
import eu.epitech.t_dev_700.repositories.AccountRepository;
import eu.epitech.t_dev_700.services.exceptions.UnknownAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String authenticate(AuthModels.LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()
                )
        );
        return jwtService
                .generateToken(accountRepository
                        .findByUsername(input.username())
                        .orElseThrow(new UnknownAccount()));
    }
}

package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.AuthModels;
import eu.epitech.t_dev_700.services.AuthService;
import eu.epitech.t_dev_700.services.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@org.springframework.context.annotation.Profile("!test")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public AuthModels.LoginResponse PostLogin(@Valid @RequestBody AuthModels.LoginRequest body) {

        String jwtToken = authService.authenticate(body);

        return new AuthModels.LoginResponse(jwtToken, jwtService.getExpirationTime());
    }
}

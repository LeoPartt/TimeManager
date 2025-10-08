package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.AuthModels;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public final class AuthController {

    @PostMapping("/login")
    public AuthModels.PostLoginResponse PostLogin(@Valid @RequestBody AuthModels.PostLoginRequest body) {
        // TODO: logic for login
        return null;
    }
}

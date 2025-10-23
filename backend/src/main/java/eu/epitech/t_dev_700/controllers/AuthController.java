package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.doc.ApiErrorResponse;
import eu.epitech.t_dev_700.models.AuthModels;
import eu.epitech.t_dev_700.services.AuthService;
import eu.epitech.t_dev_700.services.JwtService;
import eu.epitech.t_dev_700.services.exceptions.DeletedUser;
import eu.epitech.t_dev_700.services.exceptions.InvalidCredentials;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(summary = "Authenticate user")
    @ApiResponse(responseCode = "200", description = "Successfully logged in", useReturnTypeSchema = true)
    @ApiErrorResponse({InvalidCredentials.class, DeletedUser.class})
    @SecurityRequirements
    @PostMapping("/login")
    public ResponseEntity<AuthModels.LoginResponse> PostLogin(@Valid @RequestBody AuthModels.LoginRequest body) {
        String jwtToken = authService.authenticate(body);
        return ResponseEntity.ok(new AuthModels.LoginResponse(jwtToken, jwtService.getExpirationTime()));
    }
}

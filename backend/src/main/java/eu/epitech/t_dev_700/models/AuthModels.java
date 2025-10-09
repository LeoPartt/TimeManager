package eu.epitech.t_dev_700.models;

import jakarta.validation.constraints.NotNull;

public class AuthModels {

    public record LoginRequest(
            @NotNull String username,
            @NotNull String password
    ) {}

    public record LoginResponse(
            String token,
            long expiresIn
    ) {}

}

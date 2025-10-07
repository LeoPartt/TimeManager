package eu.epitech.t_dev_700.models;

import jakarta.validation.constraints.NotNull;

public class AuthModels {

    public record PostLoginRequest(
            @NotNull String username,
            @NotNull String password
    ) {}

    public record PostLoginResponse(
            String token
    ) {}

}

package eu.epitech.t_dev_700.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UserModels {

    public record User(
            Long id,
            String username,
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) {}

    //RESPONSES
    public record GetUserResponse(
            User[] users
    ) {}

    //REQUESTS
    public record PostUserRequest(
            @NotBlank String username,
            @NotBlank String password,
            @NotBlank String firstName,
            @NotBlank String lastName,
            @NotBlank @Email String email,
            @NotBlank String phoneNumber
    ) {}

    public record PutUserRequest(
            @NotEmpty String username,
            @NotEmpty String firstName,
            @NotEmpty String lastName,
            @NotEmpty String email,
            @NotEmpty String phoneNumber
    ) {}

}

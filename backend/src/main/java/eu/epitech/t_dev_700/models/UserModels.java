package eu.epitech.t_dev_700.models;

import eu.epitech.t_dev_700.models.constraints.NullableNotBlank;
import jakarta.validation.constraints.*;

public class UserModels {

    public interface User {
        String username();

        String firstName();

        String lastName();

        String email();

        String phoneNumber();
    }

    public record UserModel(
            Long id,
            String username,
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) implements User {
    }

    //REQUESTS
    public record PostUserRequest(
            @NotBlank String username,
            @NotBlank String password,
            @NotBlank String firstName,
            @NotBlank String lastName,
            @NotNull @Email String email,
            @NotNull String phoneNumber
    ) implements User {
    }

    public record PutUserRequest(
            @NotBlank String username,
            @NotBlank String firstName,
            @NotBlank String lastName,
            @NotNull String email,
            @NotNull String phoneNumber
    ) implements User {
    }

    public record PatchUserRequest(
            @NullableNotBlank String username,
            @NullableNotBlank String firstName,
            @NullableNotBlank String lastName,
            String email,
            String phoneNumber
    ) implements User {
    }

}

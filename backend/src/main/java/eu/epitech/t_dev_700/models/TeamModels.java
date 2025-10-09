package eu.epitech.t_dev_700.models;

import eu.epitech.t_dev_700.models.constraints.NullableNotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TeamModels {

    public interface Team {
        String name();
        String description();
    }

    // INTERNALS
    public record TeamModel (
            Long id,
            String name,
            String description
    ) implements Team {}

    //REQUESTS
    public record PostTeamRequest(
            @NotBlank String name,
            @NotNull String description
    ) implements Team {}

    public record PutTeamRequest(
            @NotBlank String name,
            @NotNull String description
    ) implements Team {}

    public record PatchTeamRequest(
            @NullableNotBlank String name,
            String description
    ) implements Team {}

}

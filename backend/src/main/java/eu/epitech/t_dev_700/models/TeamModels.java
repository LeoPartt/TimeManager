package eu.epitech.t_dev_700.models;

import eu.epitech.t_dev_700.models.constraints.NullableNotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TeamModels {

    public interface Team {
        String name();
        String description();
    }

    @Schema(description = "Team information model")
    public record TeamResponse(
            @Schema(description = "Unique team identifier", example = "1")
            Long id,

            @Schema(description = "Team name", example = "Development Team")
            String name,

            @Schema(description = "Team description", example = "Main development team for backend services")
            String description
    ) implements Team, HasId {}

    @Schema(description = "Request body for creating a new team")
    public record PostTeamRequest(
            @Schema(description = "Team name", example = "Development Team")
            @NotBlank String name,

            @Schema(description = "Team description", example = "Main development team for backend services")
            @NotNull String description
    ) implements Team {}

    @Schema(description = "Request body for completely replacing a team (PUT)")
    public record PutTeamRequest(
            @Schema(description = "Team name", example = "Development Team")
            @NotBlank String name,

            @Schema(description = "Team description", example = "Main development team for backend services")
            @NotNull String description
    ) implements Team {}

    @Schema(description = "Request body for partially updating a team (PATCH)")
    public record PatchTeamRequest(
            @Schema(description = "Team name (optional)", example = "Development Team")
            @NullableNotBlank String name,

            @Schema(description = "Team description (optional)", example = "Main development team for backend services")
            String description
    ) implements Team {}

}

package eu.epitech.t_dev_700.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class TeamModels {

    // INTERNALS
    public record Team(
            Long id,
            String name,
            String description
    ) {}

    //RESPONSES
    public record GetTeamResponse(
            Team[] teams
    ) {}

    //REQUESTS
    public record PostTeamRequest(
            @NotBlank String name,
            @NotBlank String description
    ) {}

    public record PutTeamRequest(
            @NotEmpty String name,
            @NotEmpty String description
    ) {}

}

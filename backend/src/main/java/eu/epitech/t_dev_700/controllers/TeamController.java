package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.TeamModels;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController("/teams")
public final class TeamController {
    @GetMapping
    public TeamModels.GetTeamResponse GetTeams() {
        // TODO: Logic for getting teams
        return null;
    }

    @PostMapping
    public TeamModels.Team PostTeam(@Valid @RequestBody TeamModels.PostTeamRequest body) {
        // TODO: Logic for creating a team
        return null;
    }

    @PutMapping("{id}")
    public void PutTeam(@PathVariable String id, @Valid @RequestBody TeamModels.PutTeamRequest body) {
        // TODO: Logic for updating a team
    }

    @DeleteMapping("{id}")
    public void DeleteTeam(@PathVariable String id) {
        // TODO: Logic for deleting a team
    }
}

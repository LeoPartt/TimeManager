package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.services.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public final class TeamController {

    private final TeamService teamService;

    @GetMapping
    public TeamModels.GetTeamResponse GetTeams() {
        return teamService.listTeams();
    }

    @PostMapping
    public TeamModels.Team PostTeam(@Valid @RequestBody TeamModels.PostTeamRequest body) {
        return teamService.createTeam(body);
    }

    @PutMapping("{id}")
    public TeamModels.Team PutTeam(@PathVariable Long id, @Valid @RequestBody TeamModels.PutTeamRequest body) {
        return teamService.updateTeam(id, body);
    }

    @DeleteMapping("{id}")
    public void DeleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}

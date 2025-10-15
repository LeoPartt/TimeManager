package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.services.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@Tag(name = "Team Management")
public class TeamController implements CRUDController<
        TeamModels.TeamModel,
        TeamModels.PostTeamRequest,
        TeamModels.PutTeamRequest,
        TeamModels.PatchTeamRequest
        > {

    private final TeamService teamService;

    @Override
    @GetMapping("{id}")
    public TeamModels.TeamModel Get(@PathVariable Long id) {
        return teamService.get(id);
    }

    @Override
    @GetMapping
    public TeamModels.TeamModel[] GetAll() {
        return teamService.list();
    }

    @Override
    @PreAuthorize("@userAuth.isManager(authentication)")
    @PostMapping
    public TeamModels.TeamModel Post(@Valid @RequestBody TeamModels.PostTeamRequest body) {
        return teamService.create(body);
    }

    @Override
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PutMapping("{id}")
    public TeamModels.TeamModel Put(@PathVariable Long id, @Valid @RequestBody TeamModels.PutTeamRequest body) {
        return teamService.replace(id, body);
    }

    @Override
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PatchMapping("{id}")
    public TeamModels.TeamModel Patch(@PathVariable Long id, @Valid @RequestBody TeamModels.PatchTeamRequest body) {
        return teamService.update(id, body);
    }

    @Override
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @DeleteMapping("{id}")
    public void Delete(@PathVariable Long id) {
        teamService.delete(id);
    }

}

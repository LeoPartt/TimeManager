package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<TeamModels.TeamModel> Get(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.get(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<TeamModels.TeamModel[]> GetAll() {
        return ResponseEntity.ok(teamService.list());
    }

    @Override
    @PreAuthorize("@userAuth.isManager(authentication)")
    @PostMapping
    public ResponseEntity<TeamModels.TeamModel> Post(@Valid @RequestBody TeamModels.PostTeamRequest body) {
        return this.created("teams", teamService.create(body));
    }

    @Override
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PutMapping("{id}")
    public ResponseEntity<TeamModels.TeamModel> Put(@PathVariable Long id, @Valid @RequestBody TeamModels.PutTeamRequest body) {
        return ResponseEntity.ok(teamService.replace(id, body));
    }

    @Override
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PatchMapping("{id}")
    public ResponseEntity<TeamModels.TeamModel> Patch(@PathVariable Long id, @Valid @RequestBody TeamModels.PatchTeamRequest body) {
        return ResponseEntity.ok(teamService.update(id, body));
    }

    @Override
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/members")
    public ResponseEntity<UserModels.UserModel[]> GetMemberships(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getByTeam(id));
    }

    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PostMapping("{id}/members/{userId}")
    public ResponseEntity<Void> PostMembership(@PathVariable Long id, @PathVariable Long userId) {
        teamService.postMembership(id, userId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @DeleteMapping("{id}/members/{userId}")
    public ResponseEntity<Void> DeleteMembership(@PathVariable Long id, @PathVariable Long userId) {
        teamService.deleteMembership(id, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/manager")
    public ResponseEntity<UserModels.UserModel> GetManager(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getManager(id));
    }

    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PatchMapping("{id}/manager/{userId}")
    public ResponseEntity<UserModels.UserModel> PatchManager(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseEntity.ok(teamService.updateManager(id, userId));
    }

    @PreAuthorize("@userAuth.isAdmin(authentication)")
    @DeleteMapping("{id}/manager")
    public ResponseEntity<Void> DeleteManager(@PathVariable Long id) {
        teamService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }
}

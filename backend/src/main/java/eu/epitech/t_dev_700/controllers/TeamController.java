package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.doc.ApiRoleProtected;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.TeamService;
import eu.epitech.t_dev_700.doc.ApiForbiddenResponse;
import eu.epitech.t_dev_700.doc.ApiUnauthorizedResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@Tag(name = "Team Management")
@ApiUnauthorizedResponse
public class TeamController implements CRUDController<
        TeamModels.TeamResponse,
        TeamModels.PostTeamRequest,
        TeamModels.PutTeamRequest,
        TeamModels.PatchTeamRequest
        > {

    private final TeamService teamService;

    @Override
    @Operation(summary = "Get a team at id")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @GetMapping("{id}")
    public ResponseEntity<TeamModels.TeamResponse> Get(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.get(id));
    }

    @Override
    @Operation(summary = "Get all teams")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @GetMapping
    public ResponseEntity<TeamModels.TeamResponse[]> GetAll() {
        return ResponseEntity.ok(teamService.list());
    }

    @Override
    @Operation(summary = "Create a team")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    @ApiRoleProtected(roles = {"Manager"})
    @PreAuthorize("@userAuth.isManager(authentication)")
    @PostMapping
    public ResponseEntity<TeamModels.TeamResponse> Post(@Valid @RequestBody TeamModels.PostTeamRequest body) {
        return this.created("teams", teamService.create(body));
    }

    @Override
    @Operation(summary = "Modify a team")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiRoleProtected(roles = {"Manager of"})
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PutMapping("{id}")
    public ResponseEntity<TeamModels.TeamResponse> Put(@PathVariable Long id, @Valid @RequestBody TeamModels.PutTeamRequest body) {
        return ResponseEntity.ok(teamService.replace(id, body));
    }

    @Override
    @Operation(summary = "Update a team")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiRoleProtected(roles = {"Manager of"})
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PatchMapping("{id}")
    public ResponseEntity<TeamModels.TeamResponse> Patch(@PathVariable Long id, @Valid @RequestBody TeamModels.PatchTeamRequest body) {
        return ResponseEntity.ok(teamService.update(id, body));
    }

    @Override
    @Operation(summary = "Delete a team")
    @ApiResponse(responseCode = "204")
    @ApiRoleProtected(roles = {"Manager of"})
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all members of a team")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @GetMapping("{id}/members")
    public ResponseEntity<UserModels.UserResponse[]> GetMemberships(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getByTeam(id));
    }

    @Operation(summary = "Add a member to a team")
    @ApiResponse(responseCode = "204")
    @ApiRoleProtected(roles = {"Manager of"})
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PostMapping("{id}/members/{userId}")
    public ResponseEntity<Void> PostMembership(@PathVariable Long id, @PathVariable Long userId) {
        teamService.postMembership(id, userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove a member from a team")
    @ApiResponse(responseCode = "204")
    @ApiRoleProtected(roles = {"Manager of"})
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @DeleteMapping("{id}/members/{userId}")
    public ResponseEntity<Void> DeleteMembership(@PathVariable Long id, @PathVariable Long userId) {
        teamService.deleteMembership(id, userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get the manager of a team")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @GetMapping("{id}/manager")
    public ResponseEntity<UserModels.UserResponse> GetManager(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getManager(id));
    }

    @Operation(summary = "Set the manager of a team")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiRoleProtected(roles = {"Manager of"})
    @PreAuthorize("@userAuth.isTeamManager(authentication, #id)")
    @PatchMapping("{id}/manager/{userId}")
    public ResponseEntity<UserModels.UserResponse> PatchManager(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseEntity.ok(teamService.updateManager(id, userId));
    }

    @Operation(summary = "Delete the manager of a team")
    @ApiResponse(responseCode = "204")
    @ApiRoleProtected(roles = {"Administrator"})
    @PreAuthorize("@userAuth.isAdmin(authentication)")
    @DeleteMapping("{id}/manager")
    public ResponseEntity<Void> DeleteManager(@PathVariable Long id) {
        teamService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }
}

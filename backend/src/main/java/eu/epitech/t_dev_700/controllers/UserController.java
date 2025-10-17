package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.doc.ApiForbiddenResponse;
import eu.epitech.t_dev_700.doc.ApiRoleProtected;
import eu.epitech.t_dev_700.doc.ApiUnauthorizedResponse;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management")
@ApiUnauthorizedResponse
public class UserController implements CRUDController<
        UserModels.UserResponse,
        UserModels.PostUserRequest,
        UserModels.PutUserRequest,
        UserModels.PatchUserRequest
        > {

    private final UserService userService;

    @Override
    @Operation(summary = "Get user at id")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiRoleProtected(roles = {"Self", "Manager"})
    @PreAuthorize("@userAuth.isSelfOrManager(authentication, #id)")
    @GetMapping("{id}")
    public ResponseEntity<UserModels.UserResponse> Get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @Override
    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiRoleProtected(roles = {"Manager"})
    @PreAuthorize("@userAuth.isManager(authentication)")
    @GetMapping
    public ResponseEntity<UserModels.UserResponse[]> GetAll() {
        return ResponseEntity.ok(userService.list());
    }

    @Override
    @Operation(summary = "Create a user")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400")
    @ApiRoleProtected(roles = {"Manager"})
    @PreAuthorize("@userAuth.isManager(authentication)")
    @PostMapping
    public ResponseEntity<UserModels.UserResponse> Post(@Valid @RequestBody UserModels.PostUserRequest body) {
        return this.created("users", userService.create(body));
    }

    @Override
    @Operation(summary = "Modify an existing user")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiRoleProtected(roles = {"Self", "Manager of"})
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @PutMapping("{id}")
    public ResponseEntity<UserModels.UserResponse> Put(@PathVariable Long id, @Valid @RequestBody UserModels.PutUserRequest body) {
        return ResponseEntity.ok(userService.replace(id, body));
    }

    @Override
    @Operation(summary = "Update an existing user")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiRoleProtected(roles = {"Self", "Manager of"})
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @PatchMapping("{id}")
    public ResponseEntity<UserModels.UserResponse> Patch(@PathVariable Long id, @Valid @RequestBody UserModels.PatchUserRequest body) {
        return ResponseEntity.ok(userService.update(id, body));
    }

    @Override
    @Operation(summary = "Delete an existing user")
    @ApiResponse(responseCode = "204")
    @ApiRoleProtected(roles = {"Self", "Manager of"})
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get current authenticated user")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @GetMapping("/me")
    public UserModels.UserResponse getMe() {
        return userService.getCurrentUser();
    }

    @Operation(summary = "Get user's clock records")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiRoleProtected(roles = {"Self", "Manager of"})
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @GetMapping("{id}/clocks")
    public ResponseEntity<Long[]> getUserClocks(@PathVariable Long id, @RequestParam("from") Optional<Long> from, @RequestParam("to") Optional<Long> to) {
        return ResponseEntity.ok(this.userService.getClocks(id, from, to));
    }

    @Operation(summary = "Get user's teams")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @GetMapping("{id}/teams")
    public ResponseEntity<TeamModels.TeamResponse[]> getTeams(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getTeams(id));
    }

}
